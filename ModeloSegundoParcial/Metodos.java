package ModeloSegundoParcial;

public interface Metodos {
    void agregarVehiculo(VehiculosRegistrados vehiculo);
    void mostrarVehiculos();
    void buscarVehiculoPorpatente(String patente);
    void buscarVehiculosPorAnio(int anio);
    void eliminarVehiculoPorpatente(String patente);
    void actualizarVehiculoPorpatente(String patente, VehiculosRegistrados nuevoVehiculo);
}
