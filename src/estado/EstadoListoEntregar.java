package estado;

import notificaciones.TipoNotificacion;
import usuarios.Cliente;
import negocio.Pedido;
import notificaciones.Notificador;
import usuarios.EmpleadoMesero;

public class EstadoListoEntregar extends Estado{

    @Override
    public void activar(Pedido pedido) {
        EmpleadoMesero mesero = pedido.getMesero();
        Cliente cliente = pedido.getCliente();

        Notificador notificadorMesero = new Notificador(mesero.getMedioNotificacion());
        Notificador notificadorCliente = new Notificador(cliente.getMedioNotificacion());

        notificadorMesero.enviarNotificacion("PEDIDO LISTO PARA ENTREGAR!", TipoNotificacion.TEXTO_PLANO, mesero);
        notificadorCliente.enviarNotificacion("PEDIDO LISTO PARA RETIRAR / DELIVERY EN CAMINO", TipoNotificacion.TEXTO_PLANO, cliente);
    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEntregado());
    }

    /*
    * meserojuan.cambiarEstado(pedido, nuevoEstado)
    *
    * */

    // CUANDO ESTA LISTO PARA ENTREGAR SE LE NOTIFICA AL MESERO Y AL CLIENTE
}
