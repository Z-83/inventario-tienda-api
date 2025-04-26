package org.milena.controladores;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.milena.modelo.proveedor;
import org.milena.repositorio.proveedorrepositorio;

public class ProveedorController {

    public ProveedorController(Gson gson) {

        path("/proveedores", () -> {

            // Crear proveedor
            post("", (req, res) -> {
                proveedor nuevoProveedor = gson.fromJson(req.body(), proveedor.class);
                proveedorrepositorio.agregar(nuevoProveedor);
                res.status(201);
                return gson.toJson(nuevoProveedor);
            });

            // Obtener todos los proveedores
            get("", (req, res) -> {
                return gson.toJson(proveedorrepositorio.obtenerTodos());
            });

            // Obtener proveedor por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                return proveedorrepositorio.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Proveedor no encontrado";
                        });
            });

            // Actualizar proveedor
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                proveedor proveedorActualizado = gson.fromJson(req.body(), proveedor.class);
                return proveedorrepositorio.actualizar(id, proveedorActualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Proveedor no encontrado para actualizar";
                        });
            });

            // Eliminar proveedor
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                if (proveedorrepositorio.eliminar(id)) {
                    return "Proveedor eliminado correctamente";
                } else {
                    res.status(404);
                    return "Proveedor no encontrado para eliminar";
                }
            });
        });
    }
}