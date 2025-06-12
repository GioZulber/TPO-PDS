import negocio.*;
import notificaciones.App;
import notificaciones.Email;
import notificaciones.MedioNotificacion;
import pagos.MetodoPago;
import controllers.ControllerPago;
import pagos.TarjetaCredito;
import pagos.TarjetaDebito;
import usuarios.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Creacion del menu
        /*Producto coca = new Bebida("Coca Cola", 2500);
        Producto agua = new Bebida("Agua", 2500);
        Producto cerveza = new Bebida("Cerveza", 3500);

        Producto rabas = new Entrada("Rabas", 10500);
        Producto provoleta = new Entrada("Provoleta", 8000);


        Producto hamburguesa = new PlatoPrincipal("Hamburguesa", 15000);
        Producto pizza = new PlatoPrincipal("Pizza", 11500);



        IngredienteAlergenico nueces = new IngredienteAlergenico("Nueces");
        IngredienteAlergenico harinaTrigo = new IngredienteAlergenico("Harina de trigo");
        pizza.agregarIngredienteAlergenico(harinaTrigo);
        Producto brownie = new Bebida("Brownie", 5000);
        Producto helado = new Bebida("Helado", 5000);
        brownie.agregarIngredienteAlergenico(nueces);
        brownie.agregarIngredienteAlergenico(harinaTrigo);


        Menu menu = new Menu();
        menu.agregarProducto(coca);
        menu.agregarProducto(agua);
        menu.agregarProducto(cerveza);
        menu.agregarProducto(rabas);
        menu.agregarProducto(provoleta);
        menu.agregarProducto(hamburguesa);
        menu.agregarProducto(pizza);
        menu.agregarProducto(helado);

        //----------------------------------------------------------------------------------------

        // Creacion Empleados y CLientes y medios de notificacion
        MedioNotificacion app = new App();
        MedioNotificacion email = new Email();


        MetodoPago creditoCliente1 = new TarjetaCredito(50000, 0);
        MetodoPago creditoCliente2 = new TarjetaCredito(100000, 35000);

        MetodoPago debitoCliente1 = new TarjetaDebito(75000);
        MetodoPago debitoCliente2 = new TarjetaDebito(20000);

        Cliente cliente1 = new Cliente("Ramiro", app, creditoCliente1);
        Cliente cliente2 = new Cliente("Juana", email, debitoCliente2);

        EmpleadoMesero mesero1 = new EmpleadoMesero("Juan", app);
        EmpleadoMesero mesero2 = new EmpleadoMesero("Martin", app);

        EmpleadoChef chef1 = new EmpleadoChef("Roberto", app);
        EmpleadoAdministrativo administrativo = new EmpleadoAdministrativo("Hector", app);

        //----------------------------------------------------------------------------------------
        LocalDate fechaInicioC1 = LocalDate.of(2025, 5, 1);
        LocalDate fechaVencimientoC1 = LocalDate.of(2025, 5, 25);
        Cupon cupon10 = new Cupon(0.1, fechaInicioC1, fechaVencimientoC1);

        ControllerPago pagoController = new ControllerPago();

        Pedido pedidoCliente1 = new Pedido(cliente1, mesero1);
        Pedido pedidoCliente2 = new Pedido(cliente2, mesero2);


        pedidoCliente1.agregarProducto(pizza);
        pedidoCliente1.agregarProducto(coca);
        pedidoCliente1.agregarProducto(helado);
        if(pagoController.pagarPedido(pedidoCliente1, cupon10)){
            System.out.println("Cliente " + pedidoCliente1.getCliente().getNombre() + " pago con exito: "  + pedidoCliente1.getTotalPedido());
        }else{
            System.out.println("Pago rechazado, intente con otro metodo");
            pedidoCliente1.getCliente().setMetodoPago(debitoCliente1);
            if(pagoController.pagarPedido(pedidoCliente1, cupon10)){
                System.out.println("Cliente " + pedidoCliente1.getCliente().getNombre() + " pago con exito: "  + pedidoCliente1.getTotalPedido());
            }
        }
        chef1.avanzarEstadoPedido(pedidoCliente1);
        System.out.println("Estado del pedido" + pedidoCliente1.getEstadoActual().toString());
        administrativo.avanzarEstadoPedido(pedidoCliente1);
        System.out.println();
        System.out.println("Estado del pedido" + pedidoCliente1.getEstadoActual().toString());
        mesero1.avanzarEstadoPedido(pedidoCliente1);
        System.out.println("Estado del pedido" + pedidoCliente1.getEstadoActual().toString());

        //----------------------------------------------------------------------------------------
        pedidoCliente2.agregarProducto(rabas);
        pedidoCliente2.agregarProducto(hamburguesa);
        pedidoCliente2.agregarProducto(brownie);
        if(pagoController.pagarPedido(pedidoCliente2)){
            System.out.println("Cliente " + pedidoCliente2.getCliente().getNombre() + " pago con exito: "  + pedidoCliente2.getTotalPedido());
        }else{
            System.out.println("Pago rechazado, intente con otro metodo");
            pedidoCliente2.getCliente().setMetodoPago(creditoCliente2);
            if(pagoController.pagarPedido(pedidoCliente2)){
                System.out.println("Cliente " + pedidoCliente2.getCliente().getNombre() + " pago con exito: "  + pedidoCliente2.getTotalPedido());
            }
        }
        chef1.avanzarEstadoPedido(pedidoCliente2);
        System.out.println("Estado del pedido" + pedidoCliente2.getEstadoActual().toString());
        administrativo.avanzarEstadoPedido(pedidoCliente2);
        System.out.println();
        System.out.println("Estado del pedido" + pedidoCliente2.getEstadoActual().toString());
        mesero1.avanzarEstadoPedido(pedidoCliente2);
        System.out.println("Estado del pedido" + pedidoCliente2.getEstadoActual().toString());
        */
    }
}