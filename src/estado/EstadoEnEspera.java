package estado;

import negocio.Pedido;
import negocio.Producto;

import java.util.List;
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

    @Override
    public double getTiempoEspera(Pedido pedido, List<Pedido> pedidos) {
        long cantidadPedidos = pedidos.stream().filter(p -> (p.getEstadoActual() instanceof EstadoEnEspera)
                || (p.getEstadoActual() instanceof EstadoEnPreparacion)).count();
        if (cantidadPedidos >= 10)
            return ((double) cantidadPedidos /10)*20 + 5;
        return 5;
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
        pedido.agregarProducto(producto);
        
        // Usar el método del pedido directamente (sin acoplamiento a controllers)
        boolean exito = pedido.pagarProductoAdicional(producto);
        if (!exito) {
            // Si el pago falla, remover el producto del pedido
            pedido.sacarProducto(producto);
            throw new IllegalStateException("No se pudo procesar el pago del producto adicional");
        }
    }
}
