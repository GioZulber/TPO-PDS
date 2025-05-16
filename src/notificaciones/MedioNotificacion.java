package notificaciones;

public abstract class MedioNotificacion {

    public MedioNotificacion(){}

    // Cualquier MedioNotificación que herede de la clase debe implementar
    // la lógica de cómo envía una notificación
    public abstract void enviarNotificacion(Notificacion notificacion);
}
