package negocio;

import java.time.LocalDate;


public class Cupon {

    private double porcentajeDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;

    public Cupon(double porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaVencimiento){
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
    }


    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public boolean validar(){
        LocalDate fechaActual = LocalDate.now();
        return !fechaActual.isBefore(fechaInicio) && !fechaActual.isAfter(fechaVencimiento);
    }



}
