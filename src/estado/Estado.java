package estado;

import negocio.Pedido;

public abstract class Estado {

    public Estado(){

    }

    public abstract void activar(Pedido pedido);
    public abstract void avanzar(Pedido pedido);

}
