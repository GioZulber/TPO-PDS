package pagos;

public class GooglePay extends MetodoPago{

    private double saldo;
    
    public GooglePay(double saldo) {
        super();
        this.saldo = saldo;
    }
    
    @Override
    public boolean pagar(double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        } else {
            return false;
        }
    }
    
    public double getSaldo() {
        return saldo;
    }
}
