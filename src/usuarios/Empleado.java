package usuarios;

import notificaciones.MedioNotificacion;

public abstract class Empleado extends Usuario {

    public Empleado(String nombre, MedioNotificacion medioNotificacion) {
        super(nombre, medioNotificacion);
    }


}
