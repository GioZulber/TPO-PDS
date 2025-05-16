package pagos;

public class TarjetaDebito extends MetodoPago {

    private double saldo;

    public TarjetaDebito(int saldo){
        super();
        this.saldo = saldo;
    }

    @Override
    public boolean pagar(double monto) {
        if(saldo - monto > 0){
            this.setSaldo(saldo - monto);
            return true;
        }

        return false;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
