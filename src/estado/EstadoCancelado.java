package estado;

import negocio.Pedido;

import java.util.List;

public class EstadoCancelado extends Estado{

    public EstadoCancelado(){super();}


    @Override
    public void activar(Pedido pedido) {

    }

    @Override
    public void avanzar(Pedido pedido) {

    }

    @Override
    public double getTiempoEspera(Pedido pedido, List<Pedido> pedidos) {
        return 0;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        return false;
    }
}
