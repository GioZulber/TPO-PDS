package pagos;

import negocio.Cupon;
import negocio.Pedido;

import java.util.Date;

public class PagoController {

    public PagoController() {

    }

    public boolean pagarPedido(Pedido pedido) {
        double totalPedido = pedido.calcularTotal();
        pedido.setTotalPedido(totalPedido);
        boolean exito = pedido.getCliente().getMetodoPago().pagar(pedido.getTotalPedido());
        if(exito) {
            pedido.avanzarEstado();
            return true;
        }
        return false;
    }

    public boolean pagarPedido(Pedido pedido,  Cupon cupon) {

        if (cupon.validar()) {
            double totalPedido = pedido.calcularTotal();
            double totalDesuento = totalPedido - totalPedido * cupon.getPorcentajeDescuento();
            pedido.setTotalPedido(totalDesuento);
            boolean exito = pedido.getCliente().getMetodoPago().pagar(pedido.getTotalPedido());
            if(exito) {
                pedido.avanzarEstado();
                return true;
            }
            return false;
        }else{
            return this.pagarPedido(pedido);
        }
    }
}
