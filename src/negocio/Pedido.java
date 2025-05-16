package negocio;

import estado.Estado;
import estado.EstadoModificable;
import usuarios.Cliente;
import usuarios.EmpleadoMesero;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Producto> productos;
    private Estado estadoActual;
    private String idOrden;
    private Cliente cliente;
    private EmpleadoMesero mesero;
    private double totalPedido;

    public Pedido(Cliente cliente, EmpleadoMesero mesero){
        productos = new ArrayList<>();
        estadoActual = new EstadoModificable();
        this.cliente = cliente;
        this.mesero = mesero;

    }

    //Delega el avanzar estado a la clase Estado
    public void avanzarEstado(){
        this.estadoActual.avanzar(this);
    }

    // El nuevo estado se setea como el estadoActual y se activan las funcionalidades
    public void cambiarEstado(Estado nuevoEstado){
        this.estadoActual = nuevoEstado;
        nuevoEstado.activar(this);
    }

    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }

    public void sacarProducto(Producto producto){
        this.productos.remove(producto);
    }

    public double calcularTotal(){
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public EmpleadoMesero getMesero() {
        return mesero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public double getTotalPedido() {
        return totalPedido;
    }
    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }
}
