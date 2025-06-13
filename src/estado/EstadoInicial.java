package estado;

import negocio.Pedido;
import negocio.Producto;

import java.util.List;

public class EstadoInicial extends Estado {

    public EstadoInicial() {super();}

    @Override
    public void activar(Pedido pedido) {
        // Aún no tiene un funcionamiento específico, pero a futuro se
        // podrían disparar ciertos eventos de ser necesario
    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnEspera());
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
        pedido.agregarProducto(producto);
    }
}
