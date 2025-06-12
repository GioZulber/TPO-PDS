package negocio;

import estado.*;
import usuarios.Cliente;
import usuarios.EmpleadoMesero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

    private int id;
    private List<Producto> productos;
    private Estado estadoActual;
    private String idOrden;
    private Cliente cliente;
    private EmpleadoMesero mesero;
    private double totalPedido;
    private Date horarioProgramado;
    private MetodoRetiro metodoRetiro;

    public Pedido(int id, Cliente cliente, EmpleadoMesero mesero){
        productos = new ArrayList<>();
        this.id = id;
        this.cliente = cliente;
        this.mesero = mesero;
        estadoActual = new EstadoInicial();

    }

    //Delega el avanzar estado a la clase Estado
    public void avanzarEstado(){
        this.estadoActual.avanzar(this);
    }

    public boolean cancelar(){
        return this.estadoActual.cancelar(this);
    };

    public void programar(Date horario){
        // FALTA PENSAR MEJOR LA LÓGICA

        this.horarioProgramado = horario;

    }

    public double getTiempoEspera(List<Pedido> pedidos){
        return this.estadoActual.getTiempoEspera(this, pedidos);
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

    public int getId() {
        return id;
    }

    public List<Producto> getProductos(){
        return this.productos;
    }

    public MetodoRetiro getMetodoRetiro() {
        return metodoRetiro;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }



}
