import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Definición de la clase Cliente que representa a cada cliente
class Cliente {
    String nombre;
    String telefono;
    String correo;
    String gustos;
    String alergias;

    // Constructor de la clase Cliente
    public Cliente(String nombre, String telefono, String correo, String gustos, String alergias) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.gustos = gustos;
        this.alergias = alergias;
    }
}

// Clase principal que gestiona el registro de clientes
public class RegistroClientes {
    private static final String NOMBRE_ARCHIVO = "registros.txt";

    public static void main(String[] args) {
        // Configuración de entrada estándar para leer desde la consola
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Lista para almacenar los clientes
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion(reader);

            switch (opcion) {
                case 1:
                    agregarCliente(reader, listaClientes);
                    break;

                case 2:
                    mostrarClientes(listaClientes);
                    break;

                case 3:
                    guardarRegistros(listaClientes);
                    System.out.println("Registros guardados en '" + NOMBRE_ARCHIVO + "'. Saliendo del programa. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija nuevamente.");
                    break;
            }

        } while (opcion != 3);

        try {
            // Cerrar el BufferedReader al salir del programa
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar el menú de opciones
    private static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Guardar y salir");
        System.out.print("Seleccione una opción: ");
    }

    // Método para obtener la opción seleccionada por el usuario
    private static int obtenerOpcion(BufferedReader reader) {
        try {
            while (true) {
                try {
                    // Leer la entrada del usuario y convertirla a un entero
                    return Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    // Manejar la excepción si la entrada no es un número
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                }
            }
        } catch (IOException e) {
            // Manejar la excepción de lectura de entrada
            e.printStackTrace();
            return 0;
        }
    }

    // Método para agregar un nuevo cliente a la lista
    private static void agregarCliente(BufferedReader reader, ArrayList<Cliente> listaClientes) {
        System.out.println("\nAgregar Cliente:");
        try {
            // Solicitar al usuario los detalles del nuevo cliente
            System.out.print("Nombre: ");
            String nombre = reader.readLine();
            System.out.print("Teléfono: ");
            String telefono = reader.readLine();
            System.out.print("Correo: ");
            String correo = reader.readLine();
            System.out.print("Gustos: ");
            String gustos = reader.readLine();
            System.out.print("Alergias: ");
            String alergias = reader.readLine();

            // Crear un nuevo objeto Cliente y agregarlo a la lista
            Cliente nuevoCliente = new Cliente(nombre, telefono, correo, gustos, alergias);
            listaClientes.add(nuevoCliente);
            System.out.println("Cliente agregado con éxito.");
        } catch (IOException e) {
            // Manejar la excepción de lectura de entrada
            e.printStackTrace();
        }
    }

    // Método para mostrar la lista actual de clientes
    private static void mostrarClientes(ArrayList<Cliente> listaClientes) {
        System.out.println("\nLista de Clientes:");
        for (Cliente cliente : listaClientes) {
            // Mostrar los detalles de cada cliente en la lista
            System.out.println("\nNombre: " + cliente.nombre);
            System.out.println("Teléfono: " + cliente.telefono);
            System.out.println("Correo: " + cliente.correo);
            System.out.println("Gustos: " + cliente.gustos);
            System.out.println("Alergias: " + cliente.alergias);
            System.out.println("-----------------------------");
        }
    }

    // Método para guardar la lista de clientes en un archivo
    private static void guardarRegistros(ArrayList<Cliente> listaClientes) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO)) {
            for (Cliente cliente : listaClientes) {
                // Escribir los detalles del cliente en el archivo
                writer.write("Nombre: " + cliente.nombre + "\n");
                writer.write("Teléfono: " + cliente.telefono + "\n");
                writer.write("Correo: " + cliente.correo + "\n");
                writer.write("Gustos: " + cliente.gustos + "\n");
                writer.write("Alergias: " + cliente.alergias + "\n");
                writer.write("-----------------------------\n");
            }
        } catch (IOException e) {
            // Manejar la excepción de escritura en el archivo
            e.printStackTrace();
        }
    }
}
