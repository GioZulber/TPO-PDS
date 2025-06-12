package negocio;

import java.util.HashSet;
import java.util.Set;

public class  Producto {

    private int id;
    private String nombre;
    private Categoria categoria;
    private double precio;
    private Set<IngredienteAlergenico> ingredientesAlergenicos;
    private double tiempoPreparacion;


    public Producto(int id, String nombre, Categoria categoria, double precio, double tiempoPreparacion){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ingredientesAlergenicos = new HashSet<>();
    }

    public double getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public double getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void agregarIngredienteAlergenico(IngredienteAlergenico i){
        this.ingredientesAlergenicos.add(i);
    }

    public void sacarIngredienteAlergenico(IngredienteAlergenico i){
        this.ingredientesAlergenicos.remove(i);
    }


    @Override
    public String toString() {
        return "Producto{" +
                "precio=" + precio +
                ", categoria=" + categoria +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
