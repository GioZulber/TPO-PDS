package notificaciones;

import usuarios.Usuario;

public class Notificacion {

    private String contenido;
    private TipoNotificacion tipoNotificacion;
    private Usuario destinatario;

    public Notificacion(String contenido, TipoNotificacion tipoNotificacion, Usuario destinatario){
        this.contenido = contenido;
        this.tipoNotificacion = tipoNotificacion;
        this.destinatario = destinatario;

    }

    public String getContenido() {
        return contenido;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }
}
