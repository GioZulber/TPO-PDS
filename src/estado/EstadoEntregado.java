package estado;

import negocio.Pedido;
import notificaciones.Email;
import notificaciones.Notificador;
import notificaciones.TipoNotificacion;

public class EstadoEntregado extends Estado{

    public EstadoEntregado() {super();}

    // Se crea y envía la factura.
    @Override
    public void activar(Pedido pedido) {
        Notificador notificador = new Notificador(new Email());
        String contenido = "FACTURA: \n-> TOTAL: " + pedido.getTotalPedido();
        notificador.enviarNotificacion(contenido, TipoNotificacion.FACTURA, pedido.getCliente());
    }

    @Override
    public void avanzar(Pedido pedido) {
        // NO HAY ESTADO SIGUIENTE
    }





}
