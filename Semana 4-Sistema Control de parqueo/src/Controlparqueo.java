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

            //validar SE HACE VALIDACION DE HORAS // RETO OPCIONAL AGREGAR HORA Y MINUTOS

            int HoraEntrada;
            int MinutoEntrada;
            int HoraSalida;
            int MinutoSalida;

            do {
                System.out.print("Hora de entrada (0-23): ");
                HoraEntrada = scanner.nextInt();

                if (HoraEntrada < 0 || HoraEntrada > 23) {
                    System.out.println("Error: la hora debe estar entre 0 y 23.");
                }

            } while (HoraEntrada < 0 || HoraEntrada > 23);

            do {
                System.out.print("Minuto de entrada (0-59): ");
                MinutoEntrada = scanner.nextInt();

                if (MinutoEntrada < 0 || MinutoEntrada > 59) {
                    System.out.println("Error: el minuto debe estar entre 0 y 59.");
                }

            } while (MinutoEntrada < 0 || MinutoEntrada > 59);

            do {
                System.out.print("Hora de salida (0-23): ");
                HoraSalida = scanner.nextInt();

                if (HoraSalida < 0 || HoraSalida > 23) {
                    System.out.println("Error: la hora debe estar entre 0 y 23.");
                }

            } while (HoraSalida < 0 || HoraSalida > 23);

            do {
                System.out.print("Minuto de salida (0-59): ");
                MinutoSalida = scanner.nextInt();

                if (MinutoSalida < 0 || MinutoSalida > 59) {
                    System.out.println("Error: el minuto debe estar entre 0 y 59.");
                }

            } while (MinutoSalida < 0 || MinutoSalida > 59);

            // CONVERTIR convertir la hora de entrada a minutos salida

            int TotalMinutosEntrada = HoraEntrada * 60 + MinutoEntrada;
            int TotalMinutosSalida = HoraSalida * 60 + MinutoSalida;

            // SALIDA SIGUIENTE aca es para colocar la salida del dia siguiente 22:50
            if (TotalMinutosSalida <= TotalMinutosEntrada) {
                TotalMinutosSalida = TotalMinutosSalida + (24 * 60);
            }

           // CALCULO calcula tiempo real
            int MinutosEstacionado = TotalMinutosSalida - TotalMinutosEntrada;

            int HorasExactas = MinutosEstacionado / 60;
            int MinutosExactos = MinutosEstacionado % 60;

           // COBRO Para cobrar hora completa y/o cualquier fracción
            int Horas = MinutosEstacionado / 60;

            if (MinutosEstacionado % 60 != 0) {
                Horas++;
            }

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
                    HorasExactas,
                    MinutosExactos,
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
        int HorasExactas,
        int MinutosExactos,
        int Horas,
        double Tarifa,
        double Subtotal,
        double Descuento,
        double Recargo,
        double TotalPagar) {

System.out.println("\n=========== COMPROBANTE ===========");
System.out.println("Placa: " + Placa);
System.out.println("Tipo: " + TipoVehiculo);
            System.out.println("Tiempo estacionado: " + HorasExactas
                    + " horas y " + MinutosExactos + " minutos");

            System.out.println("Horas cobradas: " + Horas);

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

