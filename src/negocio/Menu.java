package negocio;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    private Set<Producto> productos;

    public Menu(){
        this.productos = new HashSet<Producto>();
    }

    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }

    public void sacarProducto(Producto producto){
        this.productos.remove(producto);
    }

    public Set<Producto> getProductos() {
        return productos;
    }
}
