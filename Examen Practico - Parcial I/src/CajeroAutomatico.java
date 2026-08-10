import java.util.Scanner;

public class CajeroAutomatico {

    static String titular = "Gerson López";
    static String numeroCuenta = "XXXX"; // Coloca aquí los últimos 4 dígitos de tu carné

    static final int PIN_CORRECTO = 2026;
    static final double SALDO_INICIAL = 1000.00;
    static final double COMISION = 10.00;


    static int cantidadDepositos = 0;
    static double totalDepositado = 0.00;

    static int cantidadRetiros = 0;
    static double totalRetirado = 0.00;

    static double totalComisiones = 0.00;

    static int operacionesRechazadas = 0;
    static int opcionesInvalidas = 0;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double saldo = SALDO_INICIAL;

        System.out.println("====================================");
        System.out.println("        CAJERO AUTOMÁTICO");
        System.out.println("====================================");

        boolean accesoPermitido = validarAcceso(scanner);

        if (!accesoPermitido) {

            System.out.println("\nCuenta bloqueada durante esta sesión.");
            System.out.println("Programa finalizado.");

            scanner.close();
            return;
        }

        System.out.println("\nBienvenido(a), " + titular);

        int opcion;

       //MENÚ PRINCIPAL//
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

                    saldo = procesarDeposito(
                            scanner,
                            saldo
                    );
                    break;

                case 3:

                    // RETIRO NORMAL
                    saldo = procesarRetiro(
                            scanner,
                            saldo
                    );
                    break;

                case 4:

                    //RETIRO CON COMISIÓN
                    saldo = procesarRetiro(
                            scanner,
                            saldo,
                            COMISION
                    );
                    break;

                case 5:

                    mostrarResumen(saldo);
                    break;

                case 6:

                    System.out.println(
                            "\nMostrando resumen final de la sesión..."
                    );

                    mostrarResumen(saldo);

                    System.out.println(
                            "\nGracias por utilizar nuestro cajero automático."
                    );

                    System.out.println(
                            "Retire su tarjeta."
                    );

                    break;

                default:

                    System.out.println(
                            "\nOpción inválida."
                    );

                    opcionesInvalidas++;

                    //REGRESA INMEDIATAMENTE AL MENÚ
                    continue;
            }

        } while (opcion != 6);

        scanner.close();
    }



    public static boolean validarAcceso(
            Scanner scanner
    ) {

        boolean accesoPermitido = false;

        int intentosPermitidos = 3;

        for (int intento = 1;
             intento <= intentosPermitidos;
             intento++) {

            int pinIngresado = leerEntero(
                    scanner,
                    "\nIngrese su PIN: "
            );

            if (pinIngresado == PIN_CORRECTO) {

                System.out.println(
                        "\nPIN correcto."
                );

                accesoPermitido = true;


                break;
            }

            int intentosRestantes =
                    intentosPermitidos - intento;

            System.out.println(
                    "PIN incorrecto."
            );

            if (intentosRestantes > 0) {

                System.out.println(
                        "Intentos restantes: "
                                + intentosRestantes
                );
            }
        }

        return accesoPermitido;
    }


    // MOSTRAR MENÚ

    public static void mostrarMenu() {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "          MENÚ PRINCIPAL"
        );

        System.out.println(
                "===================================="
        );

        System.out.println(
                "1. Consultar saldo"
        );

        System.out.println(
                "2. Depositar dinero"
        );

        System.out.println(
                "3. Realizar retiro normal"
        );

        System.out.println(
                "4. Realizar retiro con comisión"
        );

        System.out.println(
                "5. Mostrar resumen de la sesión"
        );

        System.out.println(
                "6. Salir"
        );

        System.out.println(
                "===================================="
        );
    }


    // CONSULTAR SALDO

    public static void consultarSaldo(
            double saldo
    ) {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "          CONSULTA DE SALDO"
        );

        System.out.println(
                "===================================="
        );

        System.out.println(
                "Titular: " + titular
        );

        System.out.println(
                "Número de cuenta: " + numeroCuenta
        );

        System.out.printf(
                "Saldo disponible: Q%.2f%n",
                saldo
        );

        System.out.println(
                "===================================="
        );
    }



    // DEPÓSITO


    public static double procesarDeposito(
            Scanner scanner,
            double saldoActual
    ) {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "             DEPÓSITO"
        );

        System.out.println(
                "===================================="
        );

        double monto = leerDouble(
                scanner,
                "Ingrese el monto a depositar: Q"
        );

        // WHILE OBLIGATORIO
        while (monto <= 0 || monto > 5000) {

            if (monto <= 0) {

                System.out.println(
                        "Error: el monto debe ser mayor que Q0.00."
                );

            } else {

                System.out.println(
                        "Error: el depósito no puede superar Q5,000.00."
                );
            }

            monto = leerDouble(
                    scanner,
                    "Ingrese nuevamente el monto: Q"
            );
        }

        double saldoAnterior = saldoActual;

        double nuevoSaldo =
                saldoActual + monto;

        cantidadDepositos++;

        totalDepositado += monto;

        System.out.println(
                "\nDepósito realizado correctamente."
        );

        System.out.printf(
                "Monto depositado: Q%.2f%n",
                monto
        );

        System.out.printf(
                "Saldo anterior: Q%.2f%n",
                saldoAnterior
        );

        System.out.printf(
                "Saldo actualizado: Q%.2f%n",
                nuevoSaldo
        );

        return nuevoSaldo;
    }



    // RETIRO NORMAL
    // PRIMER MÉTODO SOBRECARGADO


    public static double procesarRetiro(
            Scanner scanner,
            double saldoActual
    ) {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "          RETIRO NORMAL"
        );

        System.out.println(
                "===================================="
        );

        double monto = leerDouble(
                scanner,
                "Ingrese el monto a retirar: Q"
        );

        // VALIDACIONES ACA REALIZA LAS VALIDACIONES

        if (monto <= 0) {

            System.out.println(
                    "Retiro rechazado: el monto debe ser mayor que Q0.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        if (monto % 20 != 0) {

            System.out.println(
                    "Retiro rechazado: el monto debe ser múltiplo de Q20.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        if (monto > 2000) {

            System.out.println(
                    "Retiro rechazado: el monto máximo por operación es Q2,000.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        if (monto > saldoActual) {

            System.out.println(
                    "Retiro rechazado: saldo insuficiente."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        // RETIRO APROBADO

        double saldoAnterior =
                saldoActual;

        double totalDebitado =
                monto;

        double nuevoSaldo =
                saldoActual - totalDebitado;

        cantidadRetiros++;

        totalRetirado += monto;

        System.out.println(
                "\nRetiro realizado correctamente."
        );

        System.out.printf(
                "Monto solicitado: Q%.2f%n",
                monto
        );

        System.out.printf(
                "Saldo anterior: Q%.2f%n",
                saldoAnterior
        );

        System.out.printf(
                "Total debitado: Q%.2f%n",
                totalDebitado
        );

        System.out.printf(
                "Saldo actualizado: Q%.2f%n",
                nuevoSaldo
        );

        return nuevoSaldo;
    }



    // RETIRO CON COMISIÓN
    // SEGUNDO MÉTODO SOBRECARGADO


    public static double procesarRetiro(
            Scanner scanner,
            double saldoActual,
            double comision
    ) {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "      RETIRO CON COMISIÓN"
        );

        System.out.println(
                "===================================="
        );

        double monto = leerDouble(
                scanner,
                "Ingrese el monto a retirar: Q"
        );

        // VALIDACIONES

        if (monto <= 0) {

            System.out.println(
                    "Retiro rechazado: el monto debe ser mayor que Q0.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        if (monto % 20 != 0) {

            System.out.println(
                    "Retiro rechazado: el monto debe ser múltiplo de Q20.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        if (monto > 2000) {

            System.out.println(
                    "Retiro rechazado: el monto máximo por operación es Q2,000.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        double totalDebitado =
                monto + comision;

        // Puede cubrir el retiro, pero no la comisión

        if (monto <= saldoActual &&
                totalDebitado > saldoActual) {

            System.out.println(
                    "Retiro rechazado: el saldo cubre el retiro,"
            );

            System.out.println(
                    "pero no permite cubrir la comisión de Q10.00."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        if (totalDebitado > saldoActual) {

            System.out.println(
                    "Retiro rechazado: saldo insuficiente."
            );

            operacionesRechazadas++;

            return saldoActual;
        }

        // RETIRO APROBADO

        double saldoAnterior =
                saldoActual;

        double nuevoSaldo =
                saldoActual - totalDebitado;

        cantidadRetiros++;

        totalRetirado += monto;

        totalComisiones += comision;

        System.out.println(
                "\nRetiro realizado correctamente."
        );

        System.out.printf(
                "Monto solicitado: Q%.2f%n",
                monto
        );

        System.out.printf(
                "Comisión: Q%.2f%n",
                comision
        );

        System.out.printf(
                "Total debitado: Q%.2f%n",
                totalDebitado
        );

        System.out.printf(
                "Saldo anterior: Q%.2f%n",
                saldoAnterior
        );

        System.out.printf(
                "Saldo actualizado: Q%.2f%n",
                nuevoSaldo
        );

        return nuevoSaldo;
    }


    // MOSTRAR RESUMEN


    public static void mostrarResumen(
            double saldoActual
    ) {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "        RESUMEN DE LA SESIÓN"
        );

        System.out.println(
                "===================================="
        );

        System.out.printf(
                "Saldo inicial: Q%.2f%n",
                SALDO_INICIAL
        );

        System.out.println(
                "Depósitos exitosos: "
                        + cantidadDepositos
        );

        System.out.printf(
                "Total depositado: Q%.2f%n",
                totalDepositado
        );

        System.out.println(
                "Retiros exitosos: "
                        + cantidadRetiros
        );

        System.out.printf(
                "Total entregado en retiros: Q%.2f%n",
                totalRetirado
        );

        System.out.printf(
                "Total cobrado en comisiones: Q%.2f%n",
                totalComisiones
        );

        System.out.println(
                "Operaciones rechazadas: "
                        + operacionesRechazadas
        );

        System.out.println(
                "Opciones inválidas: "
                        + opcionesInvalidas
        );

        System.out.printf(
                "Saldo actual: Q%.2f%n",
                saldoActual
        );

        System.out.println(
                "===================================="
        );
    }

    // LEER NÚMEROS ENTEROS

    public static int leerEntero(
            Scanner scanner,
            String mensaje
    ) {

        while (true) {

            System.out.print(mensaje);

            String entrada =
                    scanner.nextLine();

            try {

                return Integer.parseInt(
                        entrada
                );

            } catch (NumberFormatException error) {

                System.out.println(
                        "Error: ingrese un número entero."
                );
            }
        }
    }



    // LEER NÚMEROS DECIMALES

    public static double leerDouble(
            Scanner scanner,
            String mensaje
    ) {

        while (true) {

            System.out.print(mensaje);

            String entrada =
                    scanner.nextLine();

            entrada =
                    entrada.replace(",", ".");

            try {

                return Double.parseDouble(
                        entrada
                );

            } catch (NumberFormatException error) {

                System.out.println(
                        "Error: ingrese un monto válido."
                );
            }
        }
    }
}