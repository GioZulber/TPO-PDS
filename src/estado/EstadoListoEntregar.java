package estado;

import negocio.MetodoRetiro;
import notificaciones.TipoNotificacion;
import usuarios.Cliente;
import negocio.Pedido;
import controllers.Notificador;
import usuarios.EmpleadoMesero;

import java.util.List;

public class EstadoListoEntregar extends Estado{

    public EstadoListoEntregar() {super();}

    //Envía notificaciones al mesero y al cliente
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


    @Override
    public double getTiempoEspera(Pedido pedido, List<Pedido> pedidos) {
        switch (pedido.getMetodoRetiro()) {
            case EN_LOCAL:
                return 0;
            case DELIVERY:
                // TODO LLAMADO A API. Simulamos un valor de retorno
                return 10;
            default:
                return -1;
        }

    }

    @Override
    public boolean cancelar(Pedido pedido) {
        return false;
    }


}
