package org.milena.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.milena.modelo.empleado;

public class empleadorepositorio {

    private static final List<empleado> empleados = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    // Obtener todos los empleados
    public static List<empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    // Obtener empleado por ID
    public static Optional<empleado> obtenerPorId(int id) {
        return empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    // Agregar nuevo empleado
    public static empleado agregar(empleado nuevoEmpleado) {
        nuevoEmpleado.setId(autoId.getAndIncrement());
        empleados.add(nuevoEmpleado);
        return nuevoEmpleado;
    }

    // Actualizar empleado
    public static Optional<empleado> actualizar(int id, empleado empleadoActualizado) {
        Optional<empleado> empleadoExistenteOpt = obtenerPorId(id);
        if (empleadoExistenteOpt.isPresent()) {
            empleado empleadoExistente = empleadoExistenteOpt.get();
            empleadoExistente.setNombre(empleadoActualizado.getNombre());
            empleadoExistente.setCargo(empleadoActualizado.getCargo());
            empleadoExistente.setSalario(empleadoActualizado.getSalario());
            return Optional.of(empleadoExistente);
        }
        return Optional.empty();
    }

    // Eliminar empleado
    public static boolean eliminar(int id) {
        return empleados.removeIf(e -> e.getId() == id);
    }
}
