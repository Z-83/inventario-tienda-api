package org.milena.modelo;


public class producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private int categoriaId;
    private int proveedorId;

    public producto() {}

    public producto(String nombre, double precio, int cantidad, int categoriaId, int proveedorId) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoriaId = categoriaId;
        this.proveedorId = proveedorId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }
    public int getProveedorId() { return proveedorId; }
    public void setProveedorId(int proveedorId) { this.proveedorId = proveedorId; }
}
