package negocio;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import estado.EstadoInicial;
import services.ServicioProgramador;

public class RelojObservadorPedidos implements Runnable {

    private final List<PedidoProgramado> pedidosProgramados;
    private final ServicioProgramador servicioProgramador;
    private volatile boolean corriendo = true;

    public RelojObservadorPedidos(List<PedidoProgramado> pedidosProgramados, ServicioProgramador servicioProgramador) {
        this.pedidosProgramados = pedidosProgramados;
        this.servicioProgramador = servicioProgramador;
    }

    public void detener() {
        this.corriendo = false;
    }

    @Override
    public void run() {
        while (corriendo) {
            LocalDateTime ahora = LocalDateTime.now();

            Iterator<PedidoProgramado> iter = pedidosProgramados.iterator();
            while (iter.hasNext()) {
                PedidoProgramado pedido = iter.next();

                if (pedido.getHorarioProgramado().isBefore(ahora) &&
                        pedido.getEstadoActual() instanceof EstadoInicial) {

                    // Ahora le avisas al ServicioProgramador que procese el pedido
                    servicioProgramador.procesarPedidoProgramado(pedido);
                    iter.remove(); // Lo saco de la lista una vez procesado
                }
            }

            try {
                Thread.sleep(1000); // revisar cada segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
