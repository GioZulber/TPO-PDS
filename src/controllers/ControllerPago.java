package controllers;

import estado.EstadoInicial;
import negocio.Cupon;
import negocio.Pedido;

public class ControllerPago {

    public ControllerPago() {
    }
    
    // Recibe un pedido. Intenta pagarlo con el método de pago del
    // cliente. Si es exitoso, avanza el estado del pedido a "EnEspera"
    public boolean pagarPedido(Pedido pedido) {
        boolean exito = pedido.pagarPedidoCompleto();
        
        if (exito && pedido.getEstadoActual() instanceof EstadoInicial) {
            pedido.avanzarEstado();
        }
        
        return exito;
    }

    // Recibe un pedido y un cupón. Si el cupón es válido, continúa con
    // la misma lógica de pago. Si no es válido, se realiza el pago
    // sin cupón.
    public boolean pagarPedido(Pedido pedido, Cupon cupon) {
        boolean exito = pedido.pagarPedidoCompleto(cupon);
        
        if (exito && pedido.getEstadoActual() instanceof EstadoInicial) {
            pedido.avanzarEstado();
        }
        
        return exito;
    }
}
