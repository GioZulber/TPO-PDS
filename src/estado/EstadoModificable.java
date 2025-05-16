package estado;

import negocio.Pedido;

public class EstadoModificable extends Estado {

    @Override
    public void activar(Pedido pedido) {
        // Aún no tiene un funcionamiento específico, pero a futuro se
        // podrían disparar ciertos eventos de ser necesario

    }

    @Override
    public void avanzar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnEspera());
    }
}
