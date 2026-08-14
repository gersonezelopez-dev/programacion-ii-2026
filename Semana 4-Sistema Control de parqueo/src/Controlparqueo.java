import java.util.Scanner;

public class Controlparqueo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int CantidadVehiculos;

        //contadores LLEVO ACABO LOS CONTADORES DE TIPO DE VEHICULO

        int CantidadMotos = 0;
        int CantidadAutos = 0;
        int CantidadPickups =0;
        int TicketsPerdidos = 0;

        //acumulador ALMACENA RESULTADO DE SUMA DE VARIOS VALORES DENTRO DEL CICLO O LA ESTRUCTURA
        double TotalRecaudado = 0;

        //datos INFORMACION PARA ENCONTRAR EL PAGO MAS ALTO
        double PagoMasAlto = 0;
        String PlacaPagoMasAlto = "";
        String TipoPagoMasAlto = "";

        System.out.println("================================");
        System.out.println(" Gerson Ezequiel López Enriquez ");
        System.out.println(" Carné: 9941-25-22144           ");
        System.out.println("================================");

        System.out.println();
        System.out.println();


        System.out.println("================================");
        System.out.println("     CONTROL DE PARQUEO      ");
        System.out.println("================================");

        //validación SE VALIDA CANTIDAD DE VEHÍCULOS

        do {
            System.out.print("Ingrese la cantidad de vehículos");
            CantidadVehiculos = scanner.nextInt();

            if (CantidadVehiculos <= 0) {
                System.out.println("Error: la cantidad debe ser mayor que cero.");
            }

        } while (CantidadVehiculos <= 0);

        // procesar ACA VAMOS A PROCESAR LOS VEHÍCULOS

        for (int i = 1; i <= CantidadVehiculos; i++) {

            System.out.println("\n=========================");
            System.out.println("    VAHÍCULO No.  " + i);
            System.out.println("=========================");

            System.out.print("Ingrese número de placa: ");
            String Placa = scanner.next();

            // validar ACA SE VERIFICA TIPO DE VEHÍCULO

            int TipoVehiculo;

            do {
                System.out.println("\nTipo de vehículos");
                System.out.println("--------------------");
                System.out.println("1. Motocicleta");
                System.out.println("2. Automóvil");
                System.out.println("3. Pickup o Camioneta");
                System.out.print("Seleccione una opción: ");

                TipoVehiculo = scanner.nextInt();

                if (TipoVehiculo < 1 || TipoVehiculo > 3) {
                    System.out.println("Error: seleccione una opción entre 1 y 3.");
                }

            } while (TipoVehiculo < 1 || TipoVehiculo > 3);

            //validar SE HACE VALIDACION DE HORAS

            int Horas;

            do {
                System.out.print("Ingrese cantidad de horas estacionadas: ");
                Horas = scanner.nextInt();

                if (Horas <= 0) {
                    System.out.println("Error: las horas deben ser mayores que cero.");
                }

            } while (Horas <= 0);

            // validar VALIDACIÓN DE TICKET

            String Ticket;

            do {
                System.out.print("¿Perdió el ticket? (S/N): ");
                Ticket = scanner.next().toUpperCase();

                if (!Ticket.equals("S") && !Ticket.equals("N")) {
                System.out.println("Error: solamente puede ingresar S o N.");
            }

        } while (!Ticket.equals("S") && !Ticket.equals("N")) ;

        // cálculos ES PARA OBTENER LOS DATOS Y HACER LOS CÁLCULOS

        double Tarifa = ObtenerTarifa(TipoVehiculo);
        String NombreVehiculo = ObtenerNombreVehiculo(TipoVehiculo);

        double Subtotal = Horas * Tarifa;
        Double Descuento = CalcularDescuento(Subtotal, Horas);

        double Recargo = 0;
        double TotalPagar;

        //SOBRECARGA calculo de pago

        if (Ticket.equals("S")) {

            Recargo = 50.00;

            //Método con 3 parametros

            TotalPagar = CalcularPago(Horas, Tarifa, Recargo);

            TicketsPerdidos++;

        } else {

            // Método con 2 parametros
            TotalPagar = CalcularPago(Horas, Tarifa);
        }

        //Contadores POR TIPO

        switch (TipoVehiculo) {

        case 1:
        CantidadMotos++;
        break;

        case 2:
        CantidadAutos++;
        break;

        case 3:
        CantidadPickups++;
        break;

    }

    //Aca usaremos para acumular Dinero

    TotalRecaudado +=TotalPagar;

    //Buscar EL PAGO MÁS ALTO
    if(TotalPagar >PagoMasAlto)

    {

        PagoMasAlto = TotalPagar;
        PlacaPagoMasAlto = Placa;
        TipoPagoMasAlto = NombreVehiculo;
    }

    // MOSTRAR COMPROBANTE
    MostrarComprobante(
            Placa,
            NombreVehiculo,
            Horas,
            Tarifa,
            Subtotal,
            Descuento,
            Recargo,
            TotalPagar
            );
}
//Aca se mostrara RESUMEN FINAL
MostrarResumen(
        CantidadMotos,
        CantidadAutos,
        CantidadPickups,
        TicketsPerdidos,
        TotalRecaudado,
        PlacaPagoMasAlto,
        TipoPagoMasAlto,
        PagoMasAlto
);


scanner.close();
}

//Método en el cual bamos obtener Tarifa
public  static double ObtenerTarifa(int TipoVehiculo) {

    switch (TipoVehiculo) {

        case 1:
            return 5.00;

        case 2:
            return 8.00;

        case 3:
            return 12.00;

        default:
            return 0;
    }
}


//Método PARA OBTENER NOMBRE DEL VEHÍCULO
public static String ObtenerNombreVehiculo(int TipoVehiculo) {

    switch (TipoVehiculo) {

        case 1:
            return "Motocicleta";

        case 2:
            return "Automóvil";

        case 3:
            return "Pickup o camioneta";

        default:
            return "Desconocido";

        }
    }

    //Método PARA CALCULAR DESCUENTO
public static double CalcularDescuento(double Subtotal, int Horas) {

    if (Horas > 8) {
        return Subtotal * 0.15;
    }

    return 0;
}
// SOBRECARGA 1: SIN TICKET PERDIDO
public static double CalcularPago(int Horas, double Tarifa) {

        double Subtotal = Horas * Tarifa;
        double Descuento = CalcularDescuento(Subtotal, Horas);

        return Subtotal - Descuento;
    }
// Sobrecarga 2: CON TICKET PERDIDO
public static double CalcularPago(int Horas, double Tarifa, double Recargo) {

    double Subtotal = Horas * Tarifa;
    double Descuento = CalcularDescuento(Subtotal, Horas);

    return Subtotal - Descuento + Recargo;
}

//Método PARA MOSTRAR COMPROBANTE
public static void MostrarComprobante(
        String Placa,
        String TipoVehiculo,
        int Horas,
        double Tarifa,
        double Subtotal,
        double Descuento,
        double Recargo,
        double TotalPagar) {

System.out.println("\n=========== COMPROBANTE ===========");
System.out.println("Placa: " + Placa);
System.out.println("Tipo: " + TipoVehiculo);
System.out.println("Horas Estacionado: " + Horas);

System.out.printf("Tarifa por Hora: Q%.2f%n", Tarifa);
System.out.printf("Subtotal: Q%.2f%n", Subtotal);
System.out.printf("Descuento: Q%.2f%n", Descuento);
System.out.printf("Recargo por Ticket: Q%.2f%n", Recargo);
System.out.printf("TOTAL: Q%.2f%n", TotalPagar);

System.out.printf("=======================================");
}

//Método PARA MOSTRAR RESUMEN FINAL
public static void MostrarResumen(
        int CantidadMotos,
        int CantidadAutos,
        int CantidadPickups,
        int TicketsPerdidos,
        double TotalRecaudado,
        String PlacaPagoMasAlto,
        String TipoPagoMasAlto,
        double PagoMasAlto) {

        System.out.println("\n======================================");
        System.out.println("       RESUMEN DE LA JORNADA");
        System.out.println("======================================");

        System.out.println("Motocicletas: " + CantidadMotos);
        System.out.println("Automóviles: " + CantidadAutos);
        System.out.println("Pickups o camionetas: " + CantidadPickups);
        System.out.println("Tickets perdidos: " + TicketsPerdidos);

        System.out.printf("Total recaudado: Q%.2f%n", TotalRecaudado);

        System.out.println("\n----- PAGO MÁS ALTO -----");
        System.out.println("Placa: " + PlacaPagoMasAlto);
        System.out.println("Tipo: " + TipoPagoMasAlto);
        System.out.printf("Pago realizado: Q%.2f%n", PagoMasAlto);

        System.out.println("======================================");
    }
}

