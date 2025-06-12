package controllers;

import notificaciones.MedioNotificacion;
import notificaciones.Notificacion;
import notificaciones.TipoNotificacion;
import usuarios.Usuario;

public class Notificador {

    private MedioNotificacion medioNotificacion;


    public Notificador(MedioNotificacion medioNotificacion){
        this.medioNotificacion = medioNotificacion;
    }
    public void setMedioNotificacion(MedioNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }
    /*
    * Actúa como controller de notificación
    * Recibe información para armas una Notificación, la crea,
    * y la envía por el MedioNotificación seteado.
    * */
    public void enviarNotificacion(String contenido, TipoNotificacion tipoNotificacion, Usuario destinatario){
        Notificacion notificacion = new Notificacion(contenido, tipoNotificacion, destinatario);
        medioNotificacion.enviarNotificacion(notificacion);
    }
}
