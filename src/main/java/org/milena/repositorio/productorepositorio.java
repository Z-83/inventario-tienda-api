package org.milena.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.milena.modelo.producto;

public class productorepositorio {

    private static final List<producto> productos = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    // Obtener todos los productos
    public static List<producto> obtenerTodos() {
        return new ArrayList<>(productos); // Devuelve copia para proteger la lista original
    }

    // Obtener producto por ID
    public static Optional<producto> obtenerPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // Agregar un nuevo producto
    public static producto agregar(producto nuevoProducto) {
        nuevoProducto.setId(autoId.getAndIncrement()); // Asigna ID único automáticamente
        productos.add(nuevoProducto);
        return nuevoProducto;
    }

    // Actualizar un producto existente
    public static Optional<producto> actualizar(int id, producto productoActualizado) {
        Optional<producto> productoExistenteOpt = obtenerPorId(id);
        if (productoExistenteOpt.isPresent()) {
            producto productoExistente = productoExistenteOpt.get();
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setCantidad(productoActualizado.getCantidad());
            productoExistente.setCategoriaId(productoActualizado.getCategoriaId());
            productoExistente.setProveedorId(productoActualizado.getProveedorId());
            return Optional.of(productoExistente);
        }
        return Optional.empty();
    }

    // Eliminar un producto
    public static boolean eliminar(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }
}
