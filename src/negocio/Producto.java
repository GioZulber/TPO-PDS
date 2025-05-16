package negocio;

import java.util.HashSet;
import java.util.Set;

public abstract class  Producto {

    protected String nombre;
    protected double precio;
    protected Set<IngredienteAlergenico> ingredientesAlergenicos;


    public Producto(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientesAlergenicos = new HashSet<>();
    }

    public double getPrecio() {
        return precio;
    }

    public void agregarIngredienteAlergenico(IngredienteAlergenico i){
        this.ingredientesAlergenicos.add(i);
    }

    public void sacarIngredienteAlergenico(IngredienteAlergenico i){
        this.ingredientesAlergenicos.remove(i);
    }


}
