package usuarios;

import negocio.Pedido;
import notificaciones.MedioNotificacion;

public abstract class EmpleadoGestion extends Empleado{


    public EmpleadoGestion(String nombre, MedioNotificacion medioNotificacion) {
        super(nombre, medioNotificacion);
    }

    public void avanzarEstadoPedido(Pedido pedido){
        pedido.avanzarEstado();
    }


}
