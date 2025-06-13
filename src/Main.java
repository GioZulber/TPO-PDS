import controllers.ControllerPago;
import controllers.ControllerPedido;
import controllers.ControllerProducto;
import negocio.*;
import notificaciones.App;
import notificaciones.Email;
import notificaciones.MedioNotificacion;
import pagos.*;
import usuarios.Cliente;
import usuarios.EmpleadoAdministrativo;
import usuarios.EmpleadoChef;
import usuarios.EmpleadoMesero;
import services.PagoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PEDIDOS - TEST COMPLETO ===\n");
        
        // Simulación de variable de entorno: Mobile o Totem
        DispositivoAcceso dispositivoAcceso = DispositivoAcceso.MOBILE;
        System.out.println("📱 Dispositivo de acceso: " + dispositivoAcceso);

        // Crear Menu y ControllerProducto
    
        Menu menu = new Menu();
        ControllerProducto controllerProducto = new ControllerProducto(menu);
        
        // Crear productos usando el ControllerProducto
        controllerProducto.crearProducto(1, "Coca Cola", Categoria.BEBIDA, 2500, 0);
        controllerProducto.crearProducto(2, "Agua", Categoria.BEBIDA, 2500, 0);
        controllerProducto.crearProducto(3, "Cerveza", Categoria.BEBIDA, 3500, 0);
        
        controllerProducto.crearProducto(4, "Rabas", Categoria.ENTRADA, 10500, 10);
        controllerProducto.crearProducto(5, "Provoleta", Categoria.ENTRADA, 8000, 15);
        
        controllerProducto.crearProducto(6, "Hamburguesa", Categoria.PLATO_PRINCIPAL, 15000, 10);
        controllerProducto.crearProducto(7, "Pizza", Categoria.PLATO_PRINCIPAL, 11500, 20);
        
        controllerProducto.crearProducto(8, "Brownie", Categoria.POSTRE, 5000, 2);
        controllerProducto.crearProducto(9, "Helado", Categoria.POSTRE, 5000, 2);
        
        // Agregar ingredientes alérgénicos a productos existentes
        System.out.println("\n🔸 === AGREGANDO INGREDIENTES ALÉRGÉNICOS ===");
        controllerProducto.agregarIngredienteAlergenico(7, "Harina de trigo"); // Pizza
        controllerProducto.agregarIngredienteAlergenico(8, "Nueces"); // Brownie
        controllerProducto.agregarIngredienteAlergenico(8, "Harina de trigo"); // Brownie
        
        // Creaciónd de demás controllers y servicios
        PagoService pagoService = new PagoService();
        ControllerPedido controllerPedido = new ControllerPedido(menu, pagoService);
        ControllerPago controllerPago = new ControllerPago();

      
        // Creación de medios de notificación y metodos de pago
        MedioNotificacion app = new App();
        MedioNotificacion email = new Email();

        MetodoPago creditoCliente1 = new TarjetaCredito(50000, 0);
        MetodoPago debitoCliente2 = new TarjetaDebito(30000);
        MetodoPago mercadoPago = new MercadoPago(60000);
        MetodoPago googlePay = new GooglePay(40000);
        MetodoPago efectivo = new Efectivo(30000); // 10% descuento automático

        // Creación de usarios 
        Cliente cliente1 = new Cliente("Ramiro", app, creditoCliente1);
        Cliente clienteMP = new Cliente("Ana García", app, mercadoPago);
        Cliente clienteGP = new Cliente("Carlos López", email, googlePay);
        Cliente clienteEfectivo = new Cliente("María Rodríguez", app, efectivo);
        Cliente clienteDebito = new Cliente("Lucía Fernández", email, debitoCliente2);

        EmpleadoMesero mesero1 = new EmpleadoMesero("Juan", app);
        EmpleadoMesero mesero2 = new EmpleadoMesero("Martin", app);
        EmpleadoChef chef1 = new EmpleadoChef("Roberto", app);
        EmpleadoAdministrativo administrativo = new EmpleadoAdministrativo("Hector", app);

        // Cupón de descuento
        LocalDate fechaInicioC1 = LocalDate.of(2025, 6, 1);
        LocalDate fechaVencimientoC1 = LocalDate.of(2025, 6, 25);
        Cupon cupon10 = new Cupon(0.1, fechaInicioC1, fechaVencimientoC1);

        // ===== TEST 1: PEDIDO CON TARJETA DE CRÉDITO =====
        System.out.println("=== TEST 1: PEDIDO CON TARJETA DE CRÉDITO ===");
        
        // Crear pedido usando el controller
        controllerPedido.crearPedido(dispositivoAcceso, 1, cliente1, mesero1);
        
        System.out.println("Saldo tarjeta crédito: $" + ((TarjetaCredito)creditoCliente1).getLimiteDisponible());
        
        // Agregar productos usando el controller
        System.out.println("\n--- Agregando productos usando ControllerPedido ---");
        controllerPedido.agregarProducto(1, 7); // Pizza
        controllerPedido.agregarProducto(1, 1); // Coca Cola
        controllerPedido.agregarProducto(1, 9); // Helado
        
        // Obtener el pedido para mostrar información
        Pedido pedido1 = controllerPedido.buscarPedidoPorId(1);
        System.out.println("Total del pedido: $" + pedido1.calcularTotal());
        
        // Pagar usando el controller
        System.out.println("\n--- Procesando pago con cupón 10% ---");
        if(controllerPago.pagarPedido(pedido1, cupon10)){
            System.out.println("Cliente " + pedido1.getCliente().getNombre() + " pagó con éxito: $" + pedido1.getTotalPedido());
            System.out.println("Estado cambió a: " + pedido1.getEstadoActual().getClass().getSimpleName());
        } else {
            System.out.println("Pago rechazado");
        }

        // Test de agregar productos en EstadoEnEspera
        System.out.println("\n--- TEST: Agregando productos en Estado En Espera ---");
        try {
            controllerPedido.agregarProducto(1, 6); // Hamburguesa
            System.out.println("Hamburguesa agregada. Nuevo total: $" + pedido1.getTotalPedido());
            
            controllerPedido.agregarProducto(1, 4); // Rabas
            System.out.println("Rabas agregadas. Nuevo total: $" + pedido1.getTotalPedido());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ===== TEST 2: PEDIDO CON MERCADO PAGO =====
        System.out.println("\n=== TEST 2: PEDIDO CON MERCADO PAGO ===");
        
        controllerPedido.crearPedido(dispositivoAcceso, 2, clienteMP, mesero2);
        
        controllerPedido.agregarProducto(2, 6); // Hamburguesa
        controllerPedido.agregarProducto(2, 3); // Cerveza
        controllerPedido.agregarProducto(2, 8); // Brownie
        
        Pedido pedido2 = controllerPedido.buscarPedidoPorId(2);
        System.out.println("Total del pedido MercadoPago: $" + pedido2.calcularTotal());
        
        if(controllerPago.pagarPedido(pedido2)){
            System.out.println("Cliente " + pedido2.getCliente().getNombre() + " pagó con MercadoPago: $" + pedido2.getTotalPedido());
        } else {
            System.out.println("Pago rechazado");
        }

        // ===== TEST 3: PEDIDO CON GOOGLE PAY =====
        System.out.println("\n=== TEST 3: PEDIDO CON GOOGLE PAY ===");
        
        controllerPedido.crearPedido(dispositivoAcceso, 3, clienteGP, mesero1);
        
        controllerPedido.agregarProducto(3, 7); // Pizza
        controllerPedido.agregarProducto(3, 5); // Provoleta
        
        Pedido pedido3 = controllerPedido.buscarPedidoPorId(3);
        System.out.println("Total del pedido GooglePay: $" + pedido3.calcularTotal());
        
        if(controllerPago.pagarPedido(pedido3)){
            System.out.println("Cliente " + pedido3.getCliente().getNombre() + " pagó con GooglePay: $" + pedido3.getTotalPedido());
        } else {
            System.out.println("Pago rechazado");
        }

        // ===== TEST 4: PEDIDO CON EFECTIVO (10% DESCUENTO) =====
        System.out.println("\n=== TEST 4: PEDIDO CON EFECTIVO (10% DESCUENTO) ===");
        
        controllerPedido.crearPedido(dispositivoAcceso, 4, clienteEfectivo, mesero2);
        
        controllerPedido.agregarProducto(4, 4); // Rabas
        controllerPedido.agregarProducto(4, 1); // Coca Cola
        
        Pedido pedido4 = controllerPedido.buscarPedidoPorId(4);
        System.out.println("Total original: $" + pedido4.calcularTotal());
        System.out.println("Total con descuento efectivo: $" + pedido4.getCliente());
        
        if(controllerPago.pagarPedido(pedido4)){
            System.out.println("Cliente " + pedido4.getCliente().getNombre() + " pagó con Efectivo: $" + pedido4.getTotalPedido() );
        } else {
            System.out.println("Pago rechazado");
        }

        // ===== TEST 5: FLUJO COMPLETO DE ESTADOS =====
        System.out.println("\n=== TEST 5: FLUJO COMPLETO DE ESTADOS ===");
        
        // Avanzar estados del primer pedido
        System.out.println("Estado actual pedido 1: " + pedido1.getEstadoActual().getClass().getSimpleName());
        
        chef1.avanzarEstadoPedido(pedido1);
        System.out.println("Después del chef: " + pedido1.getEstadoActual().getClass().getSimpleName());
        
        administrativo.avanzarEstadoPedido(pedido1);
        System.out.println("Después del administrativo: " + pedido1.getEstadoActual().getClass().getSimpleName());
        
        mesero1.avanzarEstadoPedido(pedido1);
        System.out.println("Estado final: " + pedido1.getEstadoActual().getClass().getSimpleName());

        // ===== TEST 6: PROGRAMACIÓN DE PEDIDO =====
        System.out.println("\n=== TEST 6: PROGRAMACIÓN DE PEDIDO ===");
        // Crear un pedido programado
        controllerPedido.crearPedido(dispositivoAcceso, 5, clienteDebito, mesero2);
        controllerPedido.agregarProducto(5, 2); // Agua
        controllerPedido.agregarProducto(5, 6); // Hamburguesa

        Pedido pedido5 = controllerPedido.buscarPedidoPorId(5);
        System.out.println("Total del pedido programado: $" + pedido5.calcularTotal());
        System.out.println("Estado actual del pedido programado: " + pedido5.getEstadoActual().getClass().getSimpleName());
        LocalDateTime fechaProgramada = LocalDateTime.now().plusMinutes(1);
        Date fechaComoDate = java.util.Date.from(fechaProgramada.atZone(java.time.ZoneId.systemDefault()).toInstant());
        controllerPedido.programarPedido(5, fechaComoDate);
        System.out.println("Pedido programado para " + fechaProgramada);

        

        // ===== RESUMEN FINAL =====
        System.out.println("\n=== RESUMEN FINAL ===");
        System.out.println("Pedido 1 (Tarjeta Crédito): $" + pedido1.getTotalPedido() + " - Estado: " + pedido1.getEstadoActual().getClass().getSimpleName());
        System.out.println("Pedido 2 (MercadoPago): $" + pedido2.getTotalPedido() + " - Estado: " + pedido2.getEstadoActual().getClass().getSimpleName());
        System.out.println("Pedido 3 (GooglePay): $" + pedido3.getTotalPedido() + " - Estado: " + pedido3.getEstadoActual().getClass().getSimpleName());
        System.out.println("Pedido 4 (Efectivo): $" + pedido4.getTotalPedido() + " - Estado: " + pedido4.getEstadoActual().getClass().getSimpleName());
        System.out.println("Pedido 5 (Programado): $" + pedido5.getTotalPedido() + " - Estado: " + pedido5.getEstadoActual().getClass().getSimpleName());
        
        // Usamos Timer para terminar la demo luego de 2 minutos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(controllerPago.pagarPedido(pedido5)){
                    System.out.println("Pedido programado pagado exitosamente: $" + pedido5.getTotalPedido());
                } else {
                    System.out.println("Pago del pedido programado fallido.");
                }
                System.out.println("Pedido 5 (Programado): $" + pedido5.getTotalPedido() + " - Estado: " + pedido5.getEstadoActual().getClass().getSimpleName());
                chef1.avanzarEstadoPedido(pedido5);
                System.out.println("Pedido 5 después de avanzar estado: " + pedido5.getEstadoActual().getClass().getSimpleName());
                administrativo.avanzarEstadoPedido(pedido5);
                System.out.println("Pedido 5 después de administrativo: " + pedido5.getEstadoActual().getClass().getSimpleName());
                
                System.out.println(" ===== Finalizando demo =====");
                System.exit(0);
            }
        }, 2 * 60 * 1000);
    }
}