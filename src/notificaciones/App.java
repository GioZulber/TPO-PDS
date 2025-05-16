package notificaciones;

public class App extends MedioNotificacion {


    public App(){super();}


    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        System.out.println(
                "Para: " + notificacion.getDestinatario().getNombre() + "\n"
                        +  notificacion.getContenido() + "\n\n"
                        +  notificacion.getTipoNotificacion()
        );


    }
}
