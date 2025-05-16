package estado;

import negocio.Pedido;

public abstract class Estado {

    // Gestión de Estado del pedido con patrón de diseño State
    public Estado(){

    }

    // Dispara las funcionalidades del estado
    public abstract void activar(Pedido pedido);

    // Avanza al siguiente en la secuencia
    public abstract void avanzar(Pedido pedido);

}
