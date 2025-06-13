package services;

import negocio.Cupon;
import negocio.DispositivoAcceso;
import negocio.Pedido;
import negocio.Producto;

public class PagoService {
    
    public boolean procesarPagoPedidoCompleto(Pedido pedido) {
        double total = pedido.calcularTotalAPagar();
        return pedido.procesarPago(total);
    }
    
    public boolean procesarPagoPedidoCompleto(Pedido pedido, Cupon cupon) {
        if (cupon.validar()) {
            double total = pedido.calcularTotalAPagar();
            double totalConDescuento = total - (total * cupon.getPorcentajeDescuento());
            return pedido.procesarPago(totalConDescuento);
        }
        return procesarPagoPedidoCompleto(pedido);
    }
    
    public boolean procesarPagoProducto(Pedido pedido, Producto producto) {
        return pedido.procesarPago(producto.getPrecio());
    }
    
    public double calcularTotalConDescuento(Pedido pedido, Cupon cupon) {
        double total = pedido.calcularTotalAPagar();
        if (pedido.getDa() == DispositivoAcceso.MOBILE)
            return cupon.validar() ? total - (total * cupon.getPorcentajeDescuento()) : total;
        return total - (total * cupon.getPorcentajeDescuento());
    }

    
}
