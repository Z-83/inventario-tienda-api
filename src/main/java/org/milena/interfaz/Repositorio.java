package org.milena.interfaz;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T> {
    void crear(T entidad);
    Optional<T> obtenerPorId(int id);
    List<T> obtenerTodos();
    void actualizar(T entidad);
    void eliminar(int id);
}