package org.milena.controladores;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.milena.modelo.venta;
import org.milena.repositorio.ventarepositorio;

public class VentaController {

    public VentaController(Gson gson) {

        path("/ventas", () -> {

            // Crear venta
            post("", (req, res) -> {
                venta nuevaVenta = gson.fromJson(req.body(), venta.class);
                ventarepositorio.agregar(nuevaVenta);
                res.status(201);
                return gson.toJson(nuevaVenta);
            });

            // Obtener todas las ventas
            get("", (req, res) -> {
                return gson.toJson(ventarepositorio.obtenerTodos());
            });

            // Obtener venta por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                return ventarepositorio.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Venta no encontrada";
                        });
            });

            // Actualizar venta
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                venta ventaActualizada = gson.fromJson(req.body(), venta.class);
                return ventarepositorio.actualizar(id, ventaActualizada)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Venta no encontrada para actualizar";
                        });
            });

            // Eliminar venta
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                if (ventarepositorio.eliminar(id)) {
                    return "Venta eliminada correctamente";
                } else {
                    res.status(404);
                    return "Venta no encontrada para eliminar";
                }
            });
        });
    }
}