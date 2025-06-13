package negocio;

import usuarios.Cliente;
import usuarios.EmpleadoMesero;

import java.time.LocalDateTime;

import estado.Estado;
import services.PagoService;

public class PedidoProgramado extends Pedido {

    private LocalDateTime horarioProgramado;
    private Runnable callbackAlActivar;
    private Estado estado;

    public PedidoProgramado(DispositivoAcceso da,int id, Cliente cliente, EmpleadoMesero mesero, LocalDateTime horarioProgramado, PagoService pagoService) {
        super(da, id, cliente, mesero, pagoService);
        this.horarioProgramado = horarioProgramado;
    }

    public LocalDateTime getHorarioProgramado() {
        return horarioProgramado;
    }

    public void setHorarioProgramado(LocalDateTime horarioProgramado) {
        this.horarioProgramado = horarioProgramado;
    }

    public void setCallbackAlActivar(Runnable callbackAlActivar) {
        this.callbackAlActivar = callbackAlActivar;
    }

    public void activar() {
        this.avanzarEstado();
        if (callbackAlActivar != null) { callbackAlActivar.run(); }
    }

    public Estado getEstado() {
        return estado;
    }
}
