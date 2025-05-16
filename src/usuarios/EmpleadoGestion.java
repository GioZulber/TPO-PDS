package usuarios;

import negocio.Pedido;
import notificaciones.MedioNotificacion;

public abstract class EmpleadoGestion extends Empleado{


    public EmpleadoGestion(String nombre, MedioNotificacion medioNotificacion) {
        super(nombre, medioNotificacion);
    }
    // Tienen el privilegio de poder avanzar el Estado de un pedido.
    // Todas las clases que hereden de está podrán acceder al método
    public void avanzarEstadoPedido(Pedido pedido){
        pedido.avanzarEstado();
    }


}
