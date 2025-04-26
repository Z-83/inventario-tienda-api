package org.milena.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.milena.modelo.proveedor;

public class proveedorrepositorio {

    private static final List<proveedor> proveedores = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    // Obtener todos los proveedores
    public static List<proveedor> obtenerTodos() {
        return new ArrayList<>(proveedores);
    }

    // Obtener proveedor por ID
    public static Optional<proveedor> obtenerPorId(int id) {
        return proveedores.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // Agregar nuevo proveedor
    public static proveedor agregar(proveedor nuevoProveedor) {
        nuevoProveedor.setId(autoId.getAndIncrement());
        proveedores.add(nuevoProveedor);
        return nuevoProveedor;
    }

    // Actualizar proveedor
    public static Optional<proveedor> actualizar(int id, proveedor proveedorActualizado) {
        Optional<proveedor> proveedorExistenteOpt = obtenerPorId(id);
        if (proveedorExistenteOpt.isPresent()) {
            proveedor proveedorExistente = proveedorExistenteOpt.get();
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
            proveedorExistente.setContacto(proveedorActualizado.getContacto());
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
            return Optional.of(proveedorExistente);
        }
        return Optional.empty();
    }

    // Eliminar proveedor
    public static boolean eliminar(int id) {
        return proveedores.removeIf(p -> p.getId() == id);
    }
}
