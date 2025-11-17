package ModeloSegundoParcial;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class VehiculosRegistrados implements Metodos {
    private int anio;
    private String marca;
    private String modelo;
    private String patente;
    public ArrayList<VehiculosRegistrados> listaVehiculos = new ArrayList<>();

    public VehiculosRegistrados(int anio, String marca, String modelo, String patente) {
        this.anio = anio;
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
    }

    public int getAnio() {
        return anio;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public String getpatente() {
        return patente;
    }

    @Override
    public String toString() {
        String linea = " patente: " + getpatente() +
                   " | Año: " + getAnio() +
                   " | Marca: " + getMarca() +
                   " | Modelo: " + getModelo();

        String borde = "═".repeat(linea.length());

        return "╔" + borde + "╗\n" +
               "║" + linea + "║\n" +
               "╚" + borde + "╝";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehiculosRegistrados)) return false;
        VehiculosRegistrados that = (VehiculosRegistrados) o;
        return Objects.equals(patente, that.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }

    @Override
    public void agregarVehiculo(VehiculosRegistrados vehiculo) {
        listaVehiculos.add(vehiculo);
    }
    @Override
    public void mostrarVehiculos() {
        for (VehiculosRegistrados vehiculo : listaVehiculos) {
            System.out.println(vehiculo);
        }
    }
    @Override
    public void buscarVehiculoPorpatente(String patente) {
        try {
            VehiculosRegistrados encontrado = listaVehiculos.stream()
                    .filter(v -> v.getpatente().equals(patente))
                    .findFirst()
                    .orElse(null);

            if (encontrado != null) {
                System.out.println("Vehículo encontrado: " + encontrado);
            } else {
                System.out.println("Vehículo con patente " + patente + " no encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el vehículo: " + e.getMessage());
        }
    }

    @Override
    public void buscarVehiculosPorAnio(int anio) {
        try {
            ArrayList<VehiculosRegistrados> encontrados = new ArrayList<>();
            listaVehiculos.stream()
                    .filter(v -> v.getAnio() == anio)
                    .forEach(encontrados::add);
            if (encontrados.isEmpty()) {
                System.out.println("No se encontraron vehículos del año " + anio + ".");
                return;
            }else {
                System.out.println("\n===================== Vehículos del año " + anio + " =====================");
                encontrados.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar los vehículos: " + e.getMessage());
        }
    }  

    @Override
    public void eliminarVehiculoPorpatente(String patente) {
        try {
            VehiculosRegistrados eliminado = listaVehiculos.stream()
                    .filter(v -> v.getpatente().equals(patente))
                    .findFirst()
                    .orElse(null);
            if (eliminado != null) {
                listaVehiculos.remove(eliminado);
                System.out.println("Vehículo con patente " + patente + " eliminado.");
                return;
            }
            System.out.println("Vehículo con patente " + patente + " no encontrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al eliminar el vehículo: " + e.getMessage());
        }
    }
    @Override
    public void actualizarVehiculoPorpatente(String patente, VehiculosRegistrados nuevoVehiculo) {
        try {
            VehiculosRegistrados actualizable = listaVehiculos.stream()
                    .filter(v -> v.getpatente().equals(patente))
                    .findFirst()
                    .orElse(null);
            if (actualizable != null) {
                int indice = listaVehiculos.indexOf(actualizable);
                listaVehiculos.set(indice, nuevoVehiculo);
                System.out.println("Vehículo con patente " + patente + " actualizado.");
                return;
            }
            System.out.println("Vehículo con patente " + patente + " no encontrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al actualizar el vehículo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        VehiculosRegistrados vehiculo1 = new VehiculosRegistrados(2020, "Toyota", "Corolla", "ABC123");
        VehiculosRegistrados vehiculo2 = new VehiculosRegistrados(2019, "Honda", "Civic", "DEF456");
        vehiculo1.agregarVehiculo(vehiculo1);
        vehiculo1.agregarVehiculo(vehiculo2);
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        while (true) {
            Menu.mostrarMenu();
            System.out.print("Seleccione una opción del menú: ");
            opcion = scanner.nextInt();
            try {
                switch (opcion) {
                case 1:
                    System.out.print("Ingrese año del vehiculo: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine(); // limpiar salto de línea

                    System.out.print("Ingrese marca del vehiculo: ");
                    String marca = scanner.nextLine();

                    System.out.print("Ingrese modelo del vehiculo: ");
                    String modelo = scanner.nextLine();

                    System.out.print("Ingrese patente del vehiculo: ");
                    String patente = scanner.nextLine().toUpperCase();

                    VehiculosRegistrados nuevoVehiculo = new VehiculosRegistrados(anio, marca, modelo, patente);
                    vehiculo1.agregarVehiculo(nuevoVehiculo);
                    System.out.println("Vehículo agregado exitosamente.");
                    continue;
                case 2:
                    System.out.print("\n================================ Lista de vehículos =====================================\n");
                    vehiculo1.mostrarVehiculos();
                    continue;
                case 3:
                    System.out.print("Ingrese año para buscar: ");
                    anio = scanner.nextInt();
                    vehiculo1.buscarVehiculosPorAnio(anio);
                    continue;
                case 4:
                    System.out.print("Ingrese patente para buscar: ");
                    vehiculo1.buscarVehiculoPorpatente(scanner.next().toUpperCase());
                    continue;
                case 5:
                    System.out.print("Ingrese la patente del vehiculo que quiere modificar: ");
                    String patenteModificada = scanner.next().toUpperCase();

                    System.out.print("Ingrese año del vehiculo: ");
                    int anioModificado = scanner.nextInt();
                    scanner.nextLine(); // limpiar salto de línea

                    System.out.print("Ingrese marca del vehiculo: ");
                    String marcaModificado = scanner.nextLine();

                    System.out.print("Ingrese modelo del vehiculo: ");
                    String modeloModificado = scanner.nextLine();

                    System.out.print("Ingrese patente del vehiculo: ");
                    String patenteModificado = patenteModificada;

                    VehiculosRegistrados VehiculoModificado = new VehiculosRegistrados(anioModificado, marcaModificado, modeloModificado, patenteModificado);

                    vehiculo1.actualizarVehiculoPorpatente(patenteModificada, VehiculoModificado);
                    vehiculo1.mostrarVehiculos();
                    continue;
                case 6:
                    System.out.print("Ingrese patente del vehiculo a eliminar: ");
                    vehiculo1.eliminarVehiculoPorpatente(scanner.next().toUpperCase());
                    vehiculo1.mostrarVehiculos();
                    continue;
                case 7:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción del menú.");
                    continue;
                }
                break; // Salir del bucle si la opción es 6
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, seleccione una opción del menú: ");
                scanner.next(); // Limpiar el buffer de entrada
            }
        }
        scanner.close();
    }
}
