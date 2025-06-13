package pagos;

public class Efectivo extends MetodoPago{
    
    private double descuento = 0.1;
    private double dinero; 

    public Efectivo(double dinero) {
        super();
        this.dinero = dinero;
    }

    @Override
    public boolean pagar(double monto) {
        double montoConDescuento = calcularMontoConDescuento(monto);
        if (this.dinero >= montoConDescuento) {
            this.dinero -= montoConDescuento;
            this.setDinero(montoConDescuento);
            System.out.println(" Pago exitoso con Efectivo - Monto: $" + monto + " - Descuento aplicado: $" + (monto * descuento) + " - Total a pagar: $" + montoConDescuento);
            return true;
        } else {
            System.out.println(" Efectivo insuficiente - Disponible: $" + dinero + " - Requerido: $" + montoConDescuento);
            return false;
        }
    }
    
    public double calcularMontoConDescuento(double monto) {
        return monto - (monto * descuento);
    }
    
    public double getDinero() {
        return dinero;
    }
    
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDescuento() {
        return descuento;
    }
}
