package notificaciones;

import usuarios.Usuario;

public class Notificador {


    private MedioNotificacion medioNotificacion;


    public Notificador(MedioNotificacion medioNotificacion){
        this.medioNotificacion = medioNotificacion;
    }


    public void setMedioNotificacion(MedioNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }

    public void enviarNotificacion(String contenido, TipoNotificacion tipoNotificacion, Usuario destinatario){
        Notificacion notificacion = new Notificacion(contenido, tipoNotificacion, destinatario);
        medioNotificacion.enviarNotificacion(notificacion);
    }
}
