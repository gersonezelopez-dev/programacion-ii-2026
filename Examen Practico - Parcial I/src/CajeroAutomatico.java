import java.util.Scanner;

public class CajeroAutomatico {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String nombreCliente = "Gerson López";
        int pinGuardado = 1234;
        double saldo = 2500.00;

        System.out.println("====================================");
        System.out.println("        CAJERO AUTOMÁTICO");
        System.out.println("====================================");

        boolean accesoPermitido = iniciarSesion(scanner, pinGuardado);

        if (!accesoPermitido) {
            System.out.println("\nCuenta bloqueada por demasiados intentos.");
            System.out.println("Comuníquese con el banco.");
            scanner.close();
            return;
        }

        System.out.println("\nBienvenido, " + nombreCliente);

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero(
                    scanner,
                    "Seleccione una opción: "
            );

            switch (opcion) {

                case 1:
                    consultarSaldo(saldo);
                    break;

                case 2:
                    saldo = realizarRetiro(scanner, saldo);
                    break;

                case 3:
                    saldo = realizarDeposito(scanner, saldo);
                    break;

                case 4:
                    saldo = realizarTransferencia(scanner, saldo);
                    break;

                case 5:
                    pinGuardado = cambiarPin(
                            scanner,
                            pinGuardado
                    );
                    break;

                case 6:
                    System.out.println(
                            "\nGracias por utilizar nuestro cajero."
                    );
                    System.out.println("Retire su tarjeta.");
                    break;

                default:
                    System.out.println("\nOpción incorrecta.");
                    System.out.println(
                            "Seleccione una opción del 1 al 6."
                    );
                    break;
            }

        } while (opcion != 6);

        scanner.close();
    }

    public static boolean iniciarSesion(
            Scanner scanner,
            int pinGuardado
    ) {

        int intentosPermitidos = 3;

        for (int intento = 1;
             intento <= intentosPermitidos;
             intento++) {

            int pinIngresado = leerEntero(
                    scanner,
                    "\nIngrese su PIN: "
            );

            if (pinIngresado == pinGuardado) {
                System.out.println("\nPIN correcto.");
                return true;
            }

            int intentosRestantes =
                    intentosPermitidos - intento;

            System.out.println("PIN incorrecto.");

            if (intentosRestantes > 0) {
                System.out.println(
                        "Intentos restantes: "
                                + intentosRestantes
                );
            }
        }

        return false;
    }
    public static void mostrarMenu() {

        System.out.println("\n====================================");
        System.out.println("          MENÚ PRINCIPAL");
        System.out.println("====================================");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar efectivo");
        System.out.println("3. Depositar efectivo");
        System.out.println("4. Realizar transferencia");
        System.out.println("5. Cambiar PIN");
        System.out.println("6. Salir");
        System.out.println("====================================");
    }

    public static void consultarSaldo(double saldo) {
        System.out.println("\n=============================");
        System.out.println("      CONSULTA DE SALDO        ");
        System.out.println("===============================");
        System.out.printf(
                "Saldo disponible: Q%.2f%n",
                saldo
        );
    }

    public static double realizarRetiro(
            Scanner scanner,
            double saldoActual
    ){
        System.out.println("\n================================");
        System.out.println("        RETIRO DE EFECTIVO        ");
        System.out.println("==================================");

        System.out.println("1. Q100");
        System.out.println("2. Q200");
        System.out.println("3. Q500");
        System.out.println("4. Otro monto");
        System.out.println("5. Cancelar");

        int opcionRetiro = leerEntero(
                scanner,
                "Seleccione una opción: "
        );

        double monto;

        switch (opcionRetiro) {

            case 1:
                monto = 100;
                break;

            case 2:
                monto = 200;
                break;

            case 3:
                monto = 500;
                break;

            case 4:
                monto = leerDouble(
                        scanner,
                        "Ingrese el monto a retirar: Q"
                );
                break;

            case 5:
                mostrarMensaje("Retiro cancelado.");
                return saldoActual;

            default:
                mostrarMensaje(
                        "Opción de retiro incorrecta."
                );
            return saldoActual;
        }

        if (!validarMonto(monto, saldoActual)) {

            if (monto <= 0) {
                mostrarMensaje(
                        "El monto debe ser mayor que cero."
                );
            } else {
                mostrarMensaje(
                        "Saldo insuficiente."
                );
            }

            return saldoActual;
        }

        if (monto % 20 != 0) {
            mostrarMensaje(
                    "El monto debe ser múltiplo de Q20."
            );
            return saldoActual;
        }

    double nuevoSaldo = saldoActual - monto;

        mostrarMensaje(
                "Retiro realizado correctamente.",
                monto
        );

        mostrarComprobante(
                "RETIRO",
                monto,
                nuevoSaldo
        );

        return nuevoSaldo;
}

public static double realizarDeposito(
        Scanner scanner,
        double saldoActual
) {

    System.out.println("\n===============================");
    System.out.println("           DEPÓSITO              ");
    System.out.println("=================================");

    double monto = leerDouble(
            scanner,
            "Ingrese el monto a depositar: Q"
    );

    if (!validarMonto(monto)) {
        mostrarMensaje(
                "El monto debe ser mayor que cero."
        );
        return saldoActual;
    }

    double nuevoSaldo = saldoActual + monto;

    mostrarMensaje(
            "Depósito realizado correctamente.",
            monto
    );

    mostrarComprobante(
            "DEPÓSITO",
            monto,
            nuevoSaldo
    );

    return nuevoSaldo;

}

    public static double realizarTransferencia(
            Scanner scanner,
            double saldoActual
    ) {

        System.out.println("\n====================================");
        System.out.println("          TRANSFERENCIA");
        System.out.println("====================================");

        String cuentaDestino = leerTexto(
                scanner,
                "Ingrese el número de cuenta destino: "
        );

        if (cuentaDestino.isEmpty()) {
            mostrarMensaje(
                    "La cuenta no puede estar vacía."
            );
            return saldoActual;
        }

        double monto = leerDouble(
                scanner,
                "Ingrese el monto a transferir: Q"
        );

        if (!validarMonto(monto, saldoActual)) {

            if (monto <= 0) {
                mostrarMensaje(
                        "El monto debe ser mayor que cero."
                );
            } else {
                mostrarMensaje(
                        "Saldo insuficiente."
                );
            }

            return saldoActual;
        }

        System.out.println("\nCuenta destino: " + cuentaDestino);
        System.out.printf("Monto: Q%.2f%n", monto);

        String confirmacion = leerTexto(
                scanner,
                "¿Confirma la transferencia? (SI/NO): "
        );

        if (confirmacion.equalsIgnoreCase("NO")) {

            mostrarMensaje("Transferencia cancelada.");
            return saldoActual;

        } else if (!confirmacion.equalsIgnoreCase("SI")) {

            mostrarMensaje("Debe escribir únicamente SI o NO.");
            return saldoActual;
        }

        double nuevoSaldo = saldoActual - monto;

        mostrarMensaje(
                "Transferencia realizada correctamente.",
                monto
        );

        mostrarComprobante(
                "TRANSFERENCIA",
                cuentaDestino,
                monto,
                nuevoSaldo
        );

        return nuevoSaldo;
    }

    public static int cambiarPin(
            Scanner scanner,
            int pinActual
    ) {

        System.out.println("\n====================================");
        System.out.println("            CAMBIAR PIN");
        System.out.println("====================================");

        int pinIngresado = leerEntero(
                scanner,
                "Ingrese su PIN actual: "
        );

        if (pinIngresado != pinActual) {
            mostrarMensaje(
                    "El PIN actual es incorrecto."
            );
            return pinActual;
        }

        int nuevoPin = leerEntero(
                scanner,
                "Ingrese el nuevo PIN: "
        );

        if (!validarPin(nuevoPin)) {
            mostrarMensaje(
                    "El PIN debe contener cuatro dígitos."
            );
            return pinActual;
        }

        int confirmarPin = leerEntero(
                scanner,
                "Confirme el nuevo PIN: "
        );

        if (nuevoPin != confirmarPin) {
            mostrarMensaje(
                    "Los PIN ingresados no coinciden."
            );
            return pinActual;
        }

        mostrarMensaje(
                "PIN actualizado correctamente."
        );

        return nuevoPin;
    }

    public static boolean validarPin(int pin) {
        return pin >= 1000 && pin <= 9999;
    }


    //SOBRECARGA DE METODO VALIDAR MONTO
    public static boolean validarMonto(double monto) {
        return monto > 0;
    }

    public static boolean validarMonto(
            double monto,
            double saldoDisponible
    ) {
        return monto > 0 && monto <= saldoDisponible;
    }

    //SOBRECARGA DEL METODO MOSTRAR MENSAJE
    public static void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    public static void mostrarMensaje(
            String mensaje,
            double monto
    ) {
        System.out.println("\n" + mensaje);
        System.out.printf(
                "Monto de la operación: Q%.2f%n",
                monto
        );
    }

    //SOBRECARGA DEL METODO MOSTRAR COMPROBANTE
    public static void mostrarComprobante(
            String operacion,
            double monto,
            double saldo
    ) {

        System.out.println("\n====================================");
        System.out.println("            COMPROBANTE");
        System.out.println("====================================");
        System.out.println("Operación: " + operacion);
        System.out.printf("Monto: Q%.2f%n", monto);
        System.out.printf("Saldo: Q%.2f%n", saldo);
        System.out.println("====================================");
    }

    public static void mostrarComprobante(
            String operacion,
            String cuentaDestino,
            double monto,
            double saldo
    ) {

        System.out.println("\n====================================");
        System.out.println("            COMPROBANTE");
        System.out.println("====================================");
        System.out.println("Operación: " + operacion);
        System.out.println(
                "Cuenta destino: " + cuentaDestino
        );
        System.out.printf("Monto: Q%.2f%n", monto);
        System.out.printf("Saldo: Q%.2f%n", saldo);
        System.out.println("====================================");
    }

    public static int leerEntero(
            Scanner scanner,
            String mensaje
    ) {

        while (true) {

            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException error) {
                System.out.print(
                        "Error: ingrese un número entero."
                );
            }
        }
    }

    public  static double leerDouble(
            Scanner scanner,
            String mensaje
    ) {

        while (true) {

            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            entrada = entrada.replace(",", ".");

            try {
                return Double.parseDouble(entrada);

            } catch (NumberFormatException error) {

                System.out.println(
                        "Error: ingrese un monto válido."
                );
            }
        }
    }

    public static String leerTexto(
            Scanner scanner,
            String mensaje
    ) {

        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}