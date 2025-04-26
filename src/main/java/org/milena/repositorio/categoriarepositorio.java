package org.milena.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.milena.modelo.categoria;

public class categoriarepositorio {

    private static final List<categoria> categorias = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    // Obtener todas las categorías
    public static List<categoria> obtenerTodos() {
        return new ArrayList<>(categorias);
    }

    // Obtener categoría por ID
    public static Optional<categoria> obtenerPorId(int id) {
        return categorias.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    // Agregar nueva categoría
    public static categoria agregar(categoria nuevaCategoria) {
        nuevaCategoria.setId(autoId.getAndIncrement());
        categorias.add(nuevaCategoria);
        return nuevaCategoria;
    }

    // Actualizar categoría
    public static Optional<categoria> actualizar(int id, categoria categoriaActualizada) {
        Optional<categoria> categoriaExistenteOpt = obtenerPorId(id);
        if (categoriaExistenteOpt.isPresent()) {
            categoria categoriaExistente = categoriaExistenteOpt.get();
            categoriaExistente.setNombre(categoriaActualizada.getNombre());
            categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());
            return Optional.of(categoriaExistente);
        }
        return Optional.empty();
    }

    // Eliminar categoría
    public static boolean eliminar(int id) {
        return categorias.removeIf(c -> c.getId() == id);
    }
}
