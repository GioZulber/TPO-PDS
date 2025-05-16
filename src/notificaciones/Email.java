package notificaciones;

public class Email extends MedioNotificacion {


    public Email(){super();}


    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        System.out.println(
                "Para: " + notificacion.getDestinatario().getNombre() + " \n"
                        +  notificacion.getContenido() + "\n\n"
                        +  notificacion.getTipoNotificacion()

        );
    }
}
