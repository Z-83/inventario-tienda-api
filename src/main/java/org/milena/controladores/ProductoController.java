package org.milena.controladores;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.milena.modelo.producto;
import org.milena.repositorio.productorepositorio;

public class ProductoController {

    public ProductoController(Gson gson) {

        path("/productos", () -> {

            // Crear Producto
            post("", (req, res) -> {
                producto nuevoProducto = gson.fromJson(req.body(), producto.class);
                productorepositorio.agregar(nuevoProducto);
                res.status(201);
                return gson.toJson(nuevoProducto);
            });

            // Obtener todos los productos
            get("", (req, res) -> {
                return gson.toJson(productorepositorio.obtenerTodos());
            });

            // Obtener un producto por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                return productorepositorio.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Producto no encontrado";
                        });
            });

            // Actualizar un producto
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                producto productoActualizado = gson.fromJson(req.body(), producto.class);
                return productorepositorio.actualizar(id, productoActualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Producto no encontrado para actualizar";
                        });
            });

            // Eliminar un producto
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                if (productorepositorio.eliminar(id)) {
                    return "Producto eliminado correctamente";
                } else {
                    res.status(404);
                    return "Producto no encontrado para eliminar";
                }
            });
        });
    }
}
