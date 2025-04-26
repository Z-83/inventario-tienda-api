package org.milena.controladores;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.milena.modelo.empleado;
import org.milena.repositorio.empleadorepositorio;

public class EmpleadoController {

    public EmpleadoController(Gson gson) {

        path("/empleados", () -> {

            // Crear empleado
            post("", (req, res) -> {
                empleado nuevoEmpleado = gson.fromJson(req.body(), empleado.class);
                empleadorepositorio.agregar(nuevoEmpleado);
                res.status(201);
                return gson.toJson(nuevoEmpleado);
            });

            // Obtener todos los empleados
            get("", (req, res) -> {
                return gson.toJson(empleadorepositorio.obtenerTodos());
            });

            // Obtener empleado por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                return empleadorepositorio.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Empleado no encontrado";
                        });
            });

            // Actualizar empleado
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                empleado empleadoActualizado = gson.fromJson(req.body(), empleado.class);
                return empleadorepositorio.actualizar(id, empleadoActualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            res.status(404);
                            return "Empleado no encontrado para actualizar";
                        });
            });

            // Eliminar empleado
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                if (empleadorepositorio.eliminar(id)) {
                    return "Empleado eliminado correctamente";
                } else {
                    res.status(404);
                    return "Empleado no encontrado para eliminar";
                }
            });
        });
    }
}
