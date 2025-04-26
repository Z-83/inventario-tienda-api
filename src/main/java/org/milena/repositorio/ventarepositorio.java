package org.milena.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.milena.modelo.venta;

public class ventarepositorio {

    private static final List<venta> ventas = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    // Obtener todas las ventas
    public static List<venta> obtenerTodos() {
        return new ArrayList<>(ventas);
    }

    // Obtener venta por ID
    public static Optional<venta> obtenerPorId(int id) {
        return ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    // Agregar nueva venta
    public static venta agregar(venta nuevaVenta) {
        nuevaVenta.setId(autoId.getAndIncrement());
        ventas.add(nuevaVenta);
        return nuevaVenta;
    }

    // Actualizar venta
    public static Optional<venta> actualizar(int id, venta ventaActualizada) {
        Optional<venta> ventaExistenteOpt = obtenerPorId(id);
        if (ventaExistenteOpt.isPresent()) {
            venta ventaExistente = ventaExistenteOpt.get();
            ventaExistente.setProductoId(ventaActualizada.getProductoId());
            ventaExistente.setClienteId(ventaActualizada.getClienteId());
            ventaExistente.setCantidad(ventaActualizada.getCantidad());
            ventaExistente.setTotal(ventaActualizada.getTotal());
            return Optional.of(ventaExistente);
        }
        return Optional.empty();
    }

    // Eliminar venta
    public static boolean eliminar(int id) {
        return ventas.removeIf(v -> v.getId() == id);
    }
}