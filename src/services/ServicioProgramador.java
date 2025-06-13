package services;

import negocio.PedidoProgramado;
import negocio.RelojObservadorPedidos;
import java.util.ArrayList;
import java.util.List;

public class ServicioProgramador {
    private static ServicioProgramador instancia = new ServicioProgramador();
    private final List<PedidoProgramado> pedidos = new ArrayList<>();

    private ServicioProgramador() {
        Thread reloj = new Thread(new RelojObservadorPedidos(pedidos, this));
        reloj.start();
    }

    public static ServicioProgramador getInstancia() {
        return instancia;
    }

    public void agregarPedido(PedidoProgramado pedido) {
        pedidos.add(pedido);
    }
    
    // Método que será llamado por el RelojObservadorPedidos
    public void procesarPedidoProgramado(PedidoProgramado pedido) {
        // Aquí procesas el pedido (activarlo, enviarlo al sistema, etc.)
        pedido.activar();
        // Cualquier otra lógica de procesamiento que necesites
        System.out.println("Procesando pedido programado: " + pedido.getId());
    }
}
