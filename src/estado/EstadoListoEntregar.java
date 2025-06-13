package estado;

import negocio.Producto;
import notificaciones.TipoNotificacion;
import usuarios.Cliente;
import negocio.DispositivoAcceso;
import negocio.Pedido;
import controllers.Notificador;
import usuarios.EmpleadoMesero;

import java.util.List;

public class EstadoListoEntregar extends Estado{

    public EstadoListoEntregar() {super();}

    //Envía notificaciones al mesero y al cliente
    @Override
    public void activar(Pedido pedido) {
        
        Cliente cliente = pedido.getCliente();
        Notificador notificadorCliente = new Notificador(cliente.getMedioNotificacion());
        notificadorCliente.enviarNotificacion("PEDIDO LISTO PARA RETIRAR / DELIVERY EN CAMINO", TipoNotificacion.TEXTO_PLANO, cliente);
        
        if (pedido.getDa() == DispositivoAcceso.MOBILE){
            EmpleadoMesero mesero = pedido.getMesero();
            Notificador notificadorMesero = new Notificador(mesero.getMedioNotificacion());
            notificadorMesero.enviarNotificacion("PEDIDO LISTO PARA ENTREGAR!", TipoNotificacion.TEXTO_PLANO, mesero);
        }
    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEntregado());
    }


    @Override
    public double getTiempoEspera(Pedido pedido, List<Pedido> pedidos) {
        switch (pedido.getMetodoRetiro()) {
            case EN_LOCAL -> {
                return 0;
            }
            case DELIVERY -> {
                // TODO LLAMADO A API. Simulamos un valor de retorno
                return 10;
            }
            default -> {
                return -1;
            }
        }

    }

    @Override
    public boolean cancelar(Pedido pedido) {
        return false;
    }

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        throw new IllegalStateException("No se pueden agregar productos en listo entregar");
    }

}
