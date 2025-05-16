package estado;

import negocio.Pedido;

public class EstadoEnPreparacion extends Estado{

    public EstadoEnPreparacion() {super();}


    @Override
    public void activar(Pedido pedido) {
        // Aún no tiene un funcionamiento específico, pero a futuro se
        // podrían disparar nuevo eventos de ser necesario
    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoListoEntregar());
    }


}
