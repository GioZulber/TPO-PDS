package estado;

import negocio.Pedido;

import java.util.UUID;


public class EstadoEnEspera extends Estado{

    public EstadoEnEspera() {super();}

    // Le agrega un identificador único al Pedido.
    @Override
    public void activar(Pedido pedido) {
        pedido.setIdOrden(UUID.randomUUID().toString());
    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnPreparacion());
    }


    
}
