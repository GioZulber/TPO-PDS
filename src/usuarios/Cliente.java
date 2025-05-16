package usuarios;

import notificaciones.MedioNotificacion;
import pagos.MetodoPago;

public class Cliente extends Usuario {

    private MetodoPago metodoPago;
    public Cliente(String nombre, MedioNotificacion medioNotificacion, MetodoPago metodoPago) {
        super(nombre, medioNotificacion);
        this.metodoPago = metodoPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}
