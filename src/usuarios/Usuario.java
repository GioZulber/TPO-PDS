package usuarios;

import notificaciones.MedioNotificacion;

public class Usuario {

    protected String nombre;
    protected MedioNotificacion medioNotificacion;

    public Usuario(String nombre, MedioNotificacion medioNotificacion){
        this.nombre = nombre;
        this.medioNotificacion = medioNotificacion;
    }

    public MedioNotificacion getMedioNotificacion() {
        return medioNotificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMedioNotificacion(MedioNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }
}
