package estado;

import negocio.Pedido;
import negocio.Producto;

import java.util.List;

public class EstadoEnPreparacion extends Estado{

    public EstadoEnPreparacion() {super();}


    @Override
    public void activar(Pedido pedido) {
        // Aún no tiene un funcionamiento específico, pero a futuro se
        // podrían disparar nuevo eventos de ser necesario
    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoListoEntregar());
    }

    @Override
    public double getTiempoEspera(Pedido pedido, List<Pedido> pedidos) {
        return pedido.getProductos().stream().mapToDouble(Producto::getTiempoPreparacion).sum();
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        double montoReembolso = pedido.getTotalPedido() * 0.75;
        System.out.println("Pedido cancelado. Se reembolsa el 75%: $" + montoReembolso);
        pedido.cambiarEstado(new EstadoCancelado());
        return true;
    }

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        throw new IllegalStateException("No se pueden agregar productos en preparación");
    }
}
