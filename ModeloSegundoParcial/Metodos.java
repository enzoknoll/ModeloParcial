package ModeloSegundoParcial;

public interface Metodos {
    void agregarVehiculo(VehiculosRegistrados vehiculo);
    void mostrarVehiculos();
    void buscarVehiculoPorPlaca(String placa);
    void buscarVehiculosPorAnio(int anio);
    void eliminarVehiculoPorPlaca(String placa);
    void actualizarVehiculoPorPlaca(String placa, VehiculosRegistrados nuevoVehiculo);
}
