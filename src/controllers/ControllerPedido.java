package controllers;

import negocio.Menu;
import negocio.Pedido;
import negocio.Producto;
import usuarios.Cliente;
import usuarios.EmpleadoMesero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerPedido {

    private List<Pedido> pedidos;
    private Menu menu;

    public ControllerPedido(Menu menu ){
        this.pedidos = new ArrayList<>();
        this.menu = menu;
    }

    public void crearPedido(int id, Cliente cliente, EmpleadoMesero empleadoMesero){
        Pedido pedido = new Pedido(id, cliente, empleadoMesero);
        pedidos.add(pedido);
    };

    //Metodo auxiliar para el filtrado de pedidos
    private Pedido buscarPedidoPorId(int idPedido){
        return this.pedidos.stream().filter(p->p.getId() == idPedido).findFirst().orElse(null);
    };

    public void agregarProducto(int idPedido, int idProducto){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        Producto producto = this.menu.buscarProductoPorId(idProducto);
        if (pedido != null && producto != null)
            pedido.agregarProducto(producto);
    };

    public void sacarProducto(int idPedido, int idProducto){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        Producto producto = this.menu.buscarProductoPorId(idProducto);
        if (pedido != null || producto != null)
            pedido.sacarProducto(producto);
    };

    public void avanzarEstado(int idPedido){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if (pedido != null)
            pedido.avanzarEstado();
    };

    public boolean cancelarPedido(int idPedido){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if (pedido != null){
            boolean fueCancelado = pedido.cancelar();
            return fueCancelado;
        } else
            return false;
    };

    public void programarPedido(int idPedido, Date horario){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if (pedido != null)
            pedido.programar(horario);
    };

    public double getTiempoEsperaPedido(int idPedido){
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if (pedido != null){
            pedido.getTiempoEspera(pedidos);
            // FIJARSE DE IMPLEMENTAR ESTO
        }
        return 0;
    }





}
