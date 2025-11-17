package ModeloSegundoParcial;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class VehiculosRegistrados implements Metodos {
    private int anio;
    private String marca;
    private String modelo;
    private String placa;
    private int idPropietario;
    public ArrayList<VehiculosRegistrados> listaVehiculos = new ArrayList<>();

    public VehiculosRegistrados(int anio, String marca, String modelo, String placa, int idPropietario) {
        this.anio = anio;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.idPropietario = idPropietario;
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
    public String getPlaca() {
        return placa;
    }
    public int getIdPropietario() {
        return idPropietario;
    }

    @Override
    public String toString() {
        String linea = " Placa: " + getPlaca() +
                   " | Año: " + getAnio() +
                   " | Marca: " + getMarca() +
                   " | Modelo: " + getModelo() +
                   " | ID Propietario: " + getIdPropietario() + " ";

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
        return Objects.equals(placa, that.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
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
    public void buscarVehiculoPorPlaca(String placa) {
        try {
            VehiculosRegistrados encontrado = listaVehiculos.stream()
                    .filter(v -> v.getPlaca().equals(placa))
                    .findFirst()
                    .orElse(null);

            if (encontrado != null) {
                System.out.println("Vehículo encontrado: " + encontrado);
            } else {
                System.out.println("Vehículo con placa " + placa + " no encontrado.");
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
    public void eliminarVehiculoPorPlaca(String placa) {
        try {
            VehiculosRegistrados eliminado = listaVehiculos.stream()
                    .filter(v -> v.getPlaca().equals(placa))
                    .findFirst()
                    .orElse(null);
            if (eliminado != null) {
                listaVehiculos.remove(eliminado);
                System.out.println("Vehículo con placa " + placa + " eliminado.");
                return;
            }
            System.out.println("Vehículo con placa " + placa + " no encontrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al eliminar el vehículo: " + e.getMessage());
        }
    }
    @Override
    public void actualizarVehiculoPorPlaca(String placa, VehiculosRegistrados nuevoVehiculo) {
        try {
            VehiculosRegistrados actualizable = listaVehiculos.stream()
                    .filter(v -> v.getPlaca().equals(placa))
                    .findFirst()
                    .orElse(null);
            if (actualizable != null) {
                int indice = listaVehiculos.indexOf(actualizable);
                listaVehiculos.set(indice, nuevoVehiculo);
                System.out.println("Vehículo con placa " + placa + " actualizado.");
                return;
            }
            System.out.println("Vehículo con placa " + placa + " no encontrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al actualizar el vehículo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        VehiculosRegistrados vehiculo1 = new VehiculosRegistrados(2020, "Toyota", "Corolla", "ABC123", 1);
        VehiculosRegistrados vehiculo2 = new VehiculosRegistrados(2019, "Honda", "Civic", "DEF456", 2);
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

                    System.out.print("Ingrese placa del vehiculo: ");
                    String placa = scanner.nextLine().toUpperCase();

                    System.out.print("Ingrese ID del propietario: ");
                    int idPropietario = scanner.nextInt();

                    VehiculosRegistrados nuevoVehiculo = new VehiculosRegistrados(anio, marca, modelo, placa, idPropietario);
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
                    System.out.print("Ingrese placa para buscar: ");
                    vehiculo1.buscarVehiculoPorPlaca(scanner.next().toUpperCase());
                    continue;
                case 5:
                    System.out.print("Ingrese la placa del vehiculo que quiere modificar: ");
                    String placaModificada = scanner.next().toUpperCase();

                    System.out.print("Ingrese año del vehiculo: ");
                    int anioModificado = scanner.nextInt();
                    scanner.nextLine(); // limpiar salto de línea

                    System.out.print("Ingrese marca del vehiculo: ");
                    String marcaModificado = scanner.nextLine();

                    System.out.print("Ingrese modelo del vehiculo: ");
                    String modeloModificado = scanner.nextLine();

                    System.out.print("Ingrese placa del vehiculo: ");
                    String placaModificado = placaModificada;

                    System.out.print("Ingrese ID del propietario: ");
                    int idPropietarioModificado = scanner.nextInt();

                    VehiculosRegistrados VehiculoModificado = new VehiculosRegistrados(anioModificado, marcaModificado, modeloModificado, placaModificado, idPropietarioModificado);

                    vehiculo1.actualizarVehiculoPorPlaca(placaModificada, VehiculoModificado);
                    vehiculo1.mostrarVehiculos();
                    continue;
                case 6:
                    System.out.print("Ingrese placa del vehiculo a eliminar: ");
                    vehiculo1.eliminarVehiculoPorPlaca(scanner.next().toUpperCase());
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
