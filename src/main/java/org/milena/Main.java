package org.milena;

import static spark.Spark.*;
import com.google.gson.Gson;
import org.milena.controladores.ProductoController;
import org.milena.controladores.CategoriaController;
import org.milena.controladores.ClienteController;
import org.milena.controladores.EmpleadoController;
import org.milena.controladores.ProveedorController;
import org.milena.controladores.VentaController;

public class Main {
    public static void main(String[] args) {
        port(5000);
        Gson gson = new Gson();

        System.out.println("Iniciando controladores...");

        try {
            new ProductoController(gson);
            System.out.println("ProductoController cargado");

            new CategoriaController(gson);
            System.out.println("CategoriaController cargado");

            new ClienteController(gson);
            System.out.println("ClienteController cargado");

            new EmpleadoController(gson);
            System.out.println("EmpleadoController cargado");

            new ProveedorController(gson);
            System.out.println("ProveedorController cargado");

            new VentaController(gson);
            System.out.println("VentaController cargado");

            // Endpoint básico de prueba
            get("/", (req, res) -> "Inventario Tienda API funcionando...");

            System.out.println("Servidor levantado correctamente en puerto 4567");

        } catch (Exception e) {
            System.out.println("Error al cargar controladores: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
