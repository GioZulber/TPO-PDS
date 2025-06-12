package controllers;

import negocio.Cupon;
import negocio.Pedido;

public class ControllerPago {

    public ControllerPago() {

    }
    // Recibe un pedido. Intenta pargarlo con el método de pago del
    // cliente. Si es exitoso, avanza el estado del pedido a "EnEspera"
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

    // Recibe un pedido y un cupón. Si el cupón es válido, continuá con
    // la misma lógica de pago. Si no es válido, se realiza el pago
    // pago sin cupón.
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
