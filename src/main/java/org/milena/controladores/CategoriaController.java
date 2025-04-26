package org.milena.controladores;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.milena.modelo.categoria;
import org.milena.repositorio.categoriarepositorio;

public class CategoriaController {

    public CategoriaController(Gson gson) {

        path("/categorias", () -> {

            // Crear categoría
            post("", (req, res) -> {
                categoria nuevaCategoria = gson.fromJson(req.body(), categoria.class);
                categoriarepositorio.agregar(nuevaCategoria);
                res.status(201);
                return gson.toJson(nuevaCategoria);
            });

            // Obtener todas las categorías
            get("", (req, res) -> {
                return gson.toJson(categoriarepositorio.obtenerTodos());
            });

            // Obtener categoría por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                return categoriarepositorio.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Categoría no encontrada";
                        });
            });

            // Actualizar categoría
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                categoria categoriaActualizada = gson.fromJson(req.body(), categoria.class);
                return categoriarepositorio.actualizar(id, categoriaActualizada)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Categoría no encontrada para actualizar";
                        });
            });

            // Eliminar categoría
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                if (categoriarepositorio.eliminar(id)) {
                    return "Categoría eliminada correctamente";
                } else {
                    res.status(404);
                    return "Categoría no encontrada para eliminar";
                }
            });
        });
    }
}