package negocio;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    // ES UN REPOSITORIO DE PRODUCTOS

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

    public Producto buscarProductoPorId(int id){
        return this.productos.stream().filter(p->p.getId() == id).findFirst().orElse(null) ;
    }
}
