package pagos;

public class TarjetaCredito extends MetodoPago {

    private double limite;
    private double gastado;

    public TarjetaCredito(double limite, double gastado){

        super();
        this.limite = limite;
        this.gastado = gastado;
    }

    @Override
    public boolean pagar(double monto) {
        if(limite >= gastado + monto){
            this.setGastado(gastado + monto);
            return true;
        }

        return false;
    }

    public void setGastado(double gastado) {
        this.gastado = gastado;
    }
    
    public double getLimiteDisponible() {
        return limite - gastado;
    }
    
    public double getGastado() {
        return gastado;
    }
    
    public double getLimite() {
        return limite;
    }
}
