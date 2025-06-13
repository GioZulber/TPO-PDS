package estado;

import negocio.Pedido;
import negocio.Producto;
import notificaciones.Email;
import controllers.Notificador;
import notificaciones.TipoNotificacion;

import java.util.List;

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

    @Override
    public double getTiempoEspera(Pedido pedido, List<Pedido> pedidos) {
        return 0;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        return false;
    }

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        throw new IllegalStateException("No se pueden agregar productos en entregado");
    }
}
