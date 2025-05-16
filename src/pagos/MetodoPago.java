package pagos;

public abstract class MetodoPago {


    public MetodoPago(){}


    // Todos los métodos de pago concretos deberán implementar la lógica de pago
    public abstract boolean pagar(double monto);


}
