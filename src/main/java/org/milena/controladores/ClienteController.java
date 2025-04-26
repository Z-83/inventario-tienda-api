package org.milena.controladores;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.milena.modelo.cliente;
import org.milena.repositorio.clienterepositorio;

public class ClienteController {

    public ClienteController(Gson gson) {

        path("/clientes", () -> {

            // Crear cliente
            post("", (req, res) -> {
                cliente nuevoCliente = gson.fromJson(req.body(), cliente.class);
                clienterepositorio.agregar(nuevoCliente);
                res.status(201);
                return gson.toJson(nuevoCliente);
            });

            // Obtener todos los clientes
            get("", (req, res) -> {
                return gson.toJson(clienterepositorio.obtenerTodos());
            });

            // Obtener cliente por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                return clienterepositorio.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Cliente no encontrado";
                        });
            });

            // Actualizar cliente
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                cliente clienteActualizado = gson.fromJson(req.body(), cliente.class);
                return clienterepositorio.actualizar(id, clienteActualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Cliente no encontrado para actualizar";
                        });
            });

            // Eliminar cliente
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                if (clienterepositorio.eliminar(id)) {
                    return "Cliente eliminado correctamente";
                } else {
                    res.status(404);
                    return "Cliente no encontrado para eliminar";
                }
            });
        });
    }
}
