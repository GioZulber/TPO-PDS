package controllers;

import negocio.DispositivoAcceso;
import negocio.Menu;
import negocio.Pedido;
import negocio.PedidoProgramado;
import negocio.Producto;
import services.PagoService;
import services.ServicioProgramador;
import usuarios.Cliente;
import usuarios.EmpleadoMesero;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ControllerPedido {

    private List<Pedido> pedidos;
    private Menu menu;
    private PagoService pagoService;

    public ControllerPedido(Menu menu, PagoService pagoService){
        this.pedidos = new ArrayList<>();
        this.menu = menu;
        this.pagoService = pagoService;
    }

    public ControllerPedido(Menu menu) {
        this(menu, new PagoService());
    }


    public void crearPedido(DispositivoAcceso da, int id, Cliente cliente, EmpleadoMesero empleadoMesero){
        Pedido pedido = new Pedido(da, id, cliente, empleadoMesero, pagoService);
        pedidos.add(pedido);
    }

    public Pedido buscarPedidoPorId(int idPedido) {
        return this.pedidos.stream()
                .filter(p -> p.getId() == idPedido)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No se encontro un pedido con id: " + idPedido));
    }

    public void agregarProducto(int idPedido, int idProducto) {
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        Producto producto = this.menu.buscarProductoPorId(idProducto);
        if (pedido != null && producto != null) {
            pedido.getEstadoActual().agregarProducto(pedido, producto);
        }
    }

    public void sacarProducto(int idPedido, int idProducto){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        Producto producto = this.menu.buscarProductoPorId(idProducto);
        if (pedido != null || producto != null)
            pedido.sacarProducto(producto);
    }

    public void avanzarEstado(int idPedido){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if (pedido != null)
            pedido.avanzarEstado();
    }

    public boolean cancelarPedido(int idPedido) {
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        return pedido.cancelar();
    }

    public double getTiempoEsperaPedido(int idPedido){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if (pedido != null){
            pedido.getTiempoEspera(pedidos);
            
        }
        return 0;
    }

    public void programarPedido(int idPedido, Date horario) {
        Pedido original = this.buscarPedidoPorId(idPedido);

        
        if (original != null) {
            // Convertir Date a LocalDateTime
            LocalDateTime fechaHora = horario.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();

            // Crear nuevo PedidoProgramado
            PedidoProgramado pedidoProgramado = new PedidoProgramado(
                    original.getDa(),
                    original.getId(),
                    original.getCliente(),
                    original.getMesero(),
                    fechaHora,
                    original.getPagoService()
            );

            // Copiar productos del pedido original
            for (Producto producto : original.getProductos()) {
                pedidoProgramado.agregarProducto(producto);
            }
            

            /* 
             
            pedidoProgramado.setCallbackAlActivar(() -> {
                System.out.println("Pedido programado ID " + pedidoProgramado.getId() + " activado.");
            });
            
            */
            
            // Reemplazar el pedido en la lista
            pedidos.remove(original);
            pedidos.add(pedidoProgramado);

            // Agregar al servicio programador (maneja el hilo reloj)
            ServicioProgramador.getInstancia().agregarPedido(pedidoProgramado);
        }
    }
}
