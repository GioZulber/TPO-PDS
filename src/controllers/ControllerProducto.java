package controllers;

import negocio.Categoria;
import negocio.IngredienteAlergenico;
import negocio.Menu;
import negocio.Producto;


public class ControllerProducto {

    private Menu menu;

    public ControllerProducto(Menu menu){
        this.menu = menu;
    }

    public void crearProducto(int id, String nombre, Categoria categoria, double precio, double tiempoPreparacion){
        Producto producto = new Producto(id, nombre, categoria, precio, tiempoPreparacion);
        menu.agregarProducto(producto);
    }

    public void agregarIngredienteAlergenico(int idProducto, String nombreIngrediente){
        Producto producto = this.menu.buscarProductoPorId(idProducto);
        if (producto != null){
            IngredienteAlergenico ingrediente = new IngredienteAlergenico(nombreIngrediente);
            producto.agregarIngredienteAlergenico(ingrediente);}

    }

    public void eliminarProducto(int idProducto){
        Producto producto = this.menu.buscarProductoPorId(idProducto);
        if (producto != null){
            this.menu.sacarProducto(producto);
        }
    }

    public void mostrarProductos(){
        for ( Producto producto : menu.getProductos())
            System.out.println(producto);   //usa el toString()
    }


}
