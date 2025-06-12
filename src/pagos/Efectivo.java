package pagos;

public class Efectivo extends MetodoPago{

    @Override
    public boolean pagar(double monto) {
        return false;
    }
}
