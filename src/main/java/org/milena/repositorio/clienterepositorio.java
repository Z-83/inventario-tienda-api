package org.milena.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.milena.modelo.cliente;

public class clienterepositorio {

    private static final List<cliente> clientes = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    // Obtener todos los clientes
    public static List<cliente> obtenerTodos() {
        return new ArrayList<>(clientes);
    }

    // Obtener cliente por ID
    public static Optional<cliente> obtenerPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    // Agregar nuevo cliente
    public static cliente agregar(cliente nuevoCliente) {
        nuevoCliente.setId(autoId.getAndIncrement());
        clientes.add(nuevoCliente);
        return nuevoCliente;
    }

    // Actualizar cliente
    public static Optional<cliente> actualizar(int id, cliente clienteActualizado) {
        Optional<cliente> clienteExistenteOpt = obtenerPorId(id);
        if (clienteExistenteOpt.isPresent()) {
            cliente clienteExistente = clienteExistenteOpt.get();
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setCorreo(clienteActualizado.getCorreo());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            return Optional.of(clienteExistente);
        }
        return Optional.empty();
    }

    // Eliminar cliente
    public static boolean eliminar(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
