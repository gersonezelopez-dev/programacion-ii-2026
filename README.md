import java.util.Scanner;

public class CajeroAutomatico {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Datos iniciales de la cuenta
        String nombreCliente = "Gerson López";
        int pinGuardado = 1234;
        double saldo = 2500.00;

        System.out.println("====================================");
        System.out.println("       CAJERO AUTOMÁTICO JAVA");
        System.out.println("====================================");

        // Validar el inicio de sesión
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

            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    consultarSaldo(saldo);
                    break;

                case 2:
                    saldo = realizarDeposito(scanner, saldo);
                    break;

                case 3:
                    saldo = realizarRetiro(scanner, saldo);
                    break;

                case 4:
                    saldo = realizarTransferencia(scanner, saldo);
                    break;

                case 5:
                    pinGuardado = cambiarPin(scanner, pinGuardado);
                    break;

                case 6:
                    System.out.println("\nGracias por utilizar nuestro cajero automático.");
                    System.out.println("Retire su tarjeta.");
                    break;

                default:
                    System.out.println("\nOpción incorrecta.");
                    System.out.println("Seleccione una opción del 1 al 6.");
                    break;
            }

        } while (opcion != 6);

        scanner.close();
    }

    /*
     * Muestra el menú principal del cajero.
     */
    public static void mostrarMenu() {

        System.out.println("\n====================================");
        System.out.println("          MENÚ PRINCIPAL");
        System.out.println("====================================");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Retirar dinero");
        System.out.println("4. Realizar transferencia");
        System.out.println("5. Cambiar PIN");
        System.out.println("6. Salir");
        System.out.println("====================================");
    }

    /*
     * Permite tres intentos para ingresar el PIN.
     */
    public static boolean iniciarSesion(Scanner scanner, int pinGuardado) {

        int intentosPermitidos = 3;

        for (int intento = 1; intento <= intentosPermitidos; intento++) {

            int pinIngresado = leerEntero(
                    scanner,
                    "Ingrese su PIN de cuatro dígitos: "
            );

            if (pinIngresado == pinGuardado) {
                System.out.println("\nPIN correcto.");
                return true;
            }

            int intentosRestantes = intentosPermitidos - intento;

            if (intentosRestantes > 0) {
                System.out.println("PIN incorrecto.");
                System.out.println(
                        "Intentos restantes: " + intentosRestantes
                );
            }
        }

        return false;
    }

    /*
     * Consulta el saldo disponible.
     */
    public static void consultarSaldo(double saldo) {

        System.out.println("\n====================================");
        System.out.println("         CONSULTA DE SALDO");
        System.out.println("====================================");
        System.out.printf("Saldo disponible: Q%.2f%n", saldo);
    }

    /*
     * Solicita un depósito y devuelve el saldo actualizado.
     */
    public static double realizarDeposito(
            Scanner scanner,
            double saldoActual
    ) {

        System.out.println("\n====================================");
        System.out.println("             DEPÓSITO");
        System.out.println("====================================");

        double monto = leerDouble(
                scanner,
                "Ingrese el monto que desea depositar: Q"
        );

        // Utiliza el primer método sobrecargado validarMonto
        if (!validarMonto(monto)) {
            mostrarMensaje("El monto debe ser mayor que cero.");
            return saldoActual;
        }

        double nuevoSaldo = saldoActual + monto;

        mostrarMensaje("Depósito realizado correctamente.", monto);

        // Utiliza el primer método sobrecargado mostrarComprobante
        mostrarComprobante("DEPÓSITO", monto, nuevoSaldo);

        return nuevoSaldo;
    }

    /*
     * Solicita un retiro y devuelve el saldo actualizado.
     */
    public static double realizarRetiro(
            Scanner scanner,
            double saldoActual
    ) {

        System.out.println("\n====================================");
        System.out.println("              RETIRO");
        System.out.println("====================================");

        double monto = leerDouble(
                scanner,
                "Ingrese el monto que desea retirar: Q"
        );

        // Utiliza el segundo método sobrecargado validarMonto
        if (!validarMonto(monto, saldoActual)) {

            if (monto <= 0) {
                mostrarMensaje("El monto debe ser mayor que cero.");
            } else {
                mostrarMensaje("Saldo insuficiente.");
            }

            return saldoActual;
        }

        double nuevoSaldo = saldoActual - monto;

        mostrarMensaje("Retiro realizado correctamente.", monto);
        mostrarComprobante("RETIRO", monto, nuevoSaldo);

        return nuevoSaldo;
    }

    /*
     * Realiza una transferencia a otra cuenta.
     */
    public static double realizarTransferencia(
            Scanner scanner,
            double saldoActual
    ) {

        System.out.println("\n====================================");
        System.out.println("          TRANSFERENCIA");
        System.out.println("====================================");

        String numeroCuenta = leerTexto(
                scanner,
                "Ingrese el número de cuenta destino: "
        );

        if (numeroCuenta.isBlank()) {
            mostrarMensaje("El número de cuenta no puede estar vacío.");
            return saldoActual;
        }

        double monto = leerDouble(
                scanner,
                "Ingrese el monto a transferir: Q"
        );

        if (!validarMonto(monto, saldoActual)) {

            if (monto <= 0) {
                mostrarMensaje("El monto debe ser mayor que cero.");
            } else {
                mostrarMensaje("Saldo insuficiente para transferir.");
            }

            return saldoActual;
        }

        System.out.println("\nCuenta destino: " + numeroCuenta);
        System.out.printf("Monto: Q%.2f%n", monto);

        String confirmacion = leerTexto(
                scanner,
                "¿Desea confirmar la transferencia? S/N: "
        );

        if (!confirmacion.equalsIgnoreCase("S")) {
            mostrarMensaje("Transferencia cancelada.");
            return saldoActual;
        }

        double nuevoSaldo = saldoActual - monto;

        mostrarMensaje("Transferencia realizada correctamente.", monto);

        // Utiliza el segundo método sobrecargado mostrarComprobante
        mostrarComprobante(
                "TRANSFERENCIA",
                numeroCuenta,
                monto,
                nuevoSaldo
        );

        return nuevoSaldo;
    }

    /*
     * Permite cambiar el PIN.
     */
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
            mostrarMensaje("El PIN actual es incorrecto.");
            return pinActual;
        }

        int nuevoPin = leerEntero(
                scanner,
                "Ingrese el nuevo PIN de cuatro dígitos: "
        );

        if (!validarPin(nuevoPin)) {
            mostrarMensaje("El PIN debe contener exactamente cuatro dígitos.");
            return pinActual;
        }

        int confirmarPin = leerEntero(
                scanner,
                "Confirme el nuevo PIN: "
        );

        if (nuevoPin != confirmarPin) {
            mostrarMensaje("Los PIN ingresados no coinciden.");
            return pinActual;
        }

        mostrarMensaje("PIN actualizado correctamente.");

        return nuevoPin;
    }

    /*
     * Valida que el PIN tenga cuatro dígitos.
     */
    public static boolean validarPin(int pin) {
        return pin >= 1000 && pin <= 9999;
    }

    // ==================================================
    // SOBRECARGA DEL MÉTODO validarMonto
    // ==================================================

    /*
     * Primera versión:
     * valida únicamente que el monto sea positivo.
     */
    public static boolean validarMonto(double monto) {
        return monto > 0;
    }

    /*
     * Segunda versión:
     * valida que el monto sea positivo y que exista saldo.
     */
    public static boolean validarMonto(
            double monto,
            double saldoDisponible
    ) {
        return monto > 0 && monto <= saldoDisponible;
    }

    // ==================================================
    // SOBRECARGA DEL MÉTODO mostrarMensaje
    // ==================================================

    /*
     * Primera versión:
     * recibe únicamente un mensaje.
     */
    public static void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    /*
     * Segunda versión:
     * recibe un mensaje y un monto.
     */
    public static void mostrarMensaje(
            String mensaje,
            double monto
    ) {
        System.out.println("\n" + mensaje);
        System.out.printf("Monto de la operación: Q%.2f%n", monto);
    }

    // ==================================================
    // SOBRECARGA DEL MÉTODO mostrarComprobante
    // ==================================================

    /*
     * Primera versión:
     * comprobante para depósitos y retiros.
     */
    public static void mostrarComprobante(
            String tipoOperacion,
            double monto,
            double saldo
    ) {

        System.out.println("\n====================================");
        System.out.println("             COMPROBANTE");
        System.out.println("====================================");
        System.out.println("Operación: " + tipoOperacion);
        System.out.printf("Monto: Q%.2f%n", monto);
        System.out.printf("Saldo disponible: Q%.2f%n", saldo);
        System.out.println("====================================");
    }

    /*
     * Segunda versión:
     * comprobante especial para transferencias.
     */
    public static void mostrarComprobante(
            String tipoOperacion,
            String cuentaDestino,
            double monto,
            double saldo
    ) {

        System.out.println("\n====================================");
        System.out.println("             COMPROBANTE");
        System.out.println("====================================");
        System.out.println("Operación: " + tipoOperacion);
        System.out.println("Cuenta destino: " + cuentaDestino);
        System.out.printf("Monto transferido: Q%.2f%n", monto);
        System.out.printf("Saldo disponible: Q%.2f%n", saldo);
        System.out.println("====================================");
    }

    /*
     * Lee y valida números enteros.
     */
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
                System.out.println(
                        "Error: debe ingresar un número entero."
                );
            }
        }
    }

    /*
     * Lee y valida números decimales.
     */
    public static double leerDouble(
            Scanner scanner,
            String mensaje
    ) {

        while (true) {

            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            // Permite escribir 100.50 o 100,50
            entrada = entrada.replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException error) {
                System.out.println(
                        "Error: ingrese un monto numérico válido."
                );
            }
        }
    }

    /*
     * Lee datos de texto.
     */
    public static String leerTexto(
            Scanner scanner,
            String mensaje
    ) {

        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}
