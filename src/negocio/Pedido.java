package negocio;

import estado.*;
import services.PagoService;
import usuarios.Cliente;
import usuarios.EmpleadoMesero;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private DispositivoAcceso da;
    private int id;
    private List<Producto> productos;
    private List<Producto> productosPagados;
    private Estado estadoActual;
    private String idOrden;
    private Cliente cliente;
    private EmpleadoMesero mesero;
    private double totalPedido;
    private MetodoRetiro metodoRetiro;
    private PagoService pagoService;

    public Pedido(DispositivoAcceso da, int id, Cliente cliente, EmpleadoMesero mesero, PagoService pagoService){
        productos = new ArrayList<>();
        productosPagados = new ArrayList<>();
        this.da = da;
        this.id = id;
        this.cliente = cliente;
        this.mesero = mesero;
        this.pagoService = pagoService;
        estadoActual = new EstadoInicial();
    }

    //Delega el avanzar estado a la clase Estado
    public void avanzarEstado(){
        this.estadoActual.avanzar(this);
    }

    public boolean cancelar(){
        return this.estadoActual.cancelar(this);
    };

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

    public void agregarProductosPagados(List<Producto> productosPagados){ this.productosPagados.addAll(productosPagados); }

    public void sacarProducto(Producto producto){
        this.productos.remove(producto);
    }

    public double calcularTotal(){
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public double calcularTotalAPagar() {
        List<Producto> productosAPagar = this.getProductos().stream().filter(
                producto -> !this.getProductosPagados().contains(producto)
        ).toList();

        return productosAPagar.stream().mapToDouble(Producto::getPrecio).sum();
    }
    
    public boolean procesarPago(double monto) {
        return this.cliente.realizarPago(monto);
    }
    
    public boolean pagarPedidoCompleto() {
        boolean exito = pagoService.procesarPagoPedidoCompleto(this);
        
        if (exito) {
            double total = this.calcularTotalAPagar();
            this.setTotalPedido(total);
            this.agregarProductosPagados(this.productos);
            return true;
        }
        return false;
    }

    public boolean pagarPedidoCompleto(Cupon cupon) {
        boolean exito = pagoService.procesarPagoPedidoCompleto(this, cupon);
        
        if (exito) {
            double totalConDescuento = pagoService.calcularTotalConDescuento(this, cupon);
            this.setTotalPedido(totalConDescuento);
            this.agregarProductosPagados(this.productos);
            return true;
        }
        return false;
    }

    public boolean pagarProductoAdicional(Producto producto) {
        boolean exito = pagoService.procesarPagoProducto(this, producto);
        
        if (exito) {
            this.setTotalPedido(this.totalPedido + producto.getPrecio());
            this.agregarProductosPagados(List.of(producto));
            return true;
        }
        return false;
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

    public DispositivoAcceso getDa() {
        return da;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public List<Producto> getProductosPagados() { return productosPagados;}

    public PagoService getPagoService() {
        return pagoService;
    }
}