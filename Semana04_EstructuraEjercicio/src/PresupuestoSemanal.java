import java.util.Scanner;

public class PresupuestoSemanal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su presupuesto semanal: Q");
        double presupuesto = scanner.nextDouble();

        System.out.print("Gasto de alimentación: Q");
        double alimentacion = scanner.nextDouble();

        System.out.print("Gasto de transporte: Q");
        double transporte = scanner.nextDouble();

        System.out.print("Otros gastos: Q");
        double otros = scanner.nextDouble();

        // Llamar métodos
        double totalGastos = calcularTotalGastos(alimentacion, transporte, otros);
        double saldo = calcularSaldo(presupuesto, totalGastos);
        String estado = obtenerEstado(saldo);

        mostrarResumen(nombre, presupuesto, totalGastos, saldo, estado);

        scanner.close();
    }

    public static double calcularTotalGastos(double alimentacion,
                                             double transporte,
                                             double otros) {
        return alimentacion + transporte + otros;
    }

    public static double calcularSaldo(double presupuesto, double totalGastos) {
        return presupuesto - totalGastos;
    }

    public static String obtenerEstado(double saldo) {
        if (saldo > 0) {
            return "Le sobra dinero.";
        } else if (saldo == 0) {
            return "Gastó exactamente su presupuesto.";
        } else {
            return "Se pasó del presupuesto.";
        }
    }

    public static void mostrarResumen(String nombre, double presupuesto,
                                      double totalGastos, double saldo,
                                      String estado) {

        System.out.println("\n===== RESUMEN SEMANAL =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Presupuesto: Q" + presupuesto);
        System.out.println("Total de gastos: Q" + totalGastos);
        System.out.println("Saldo: Q" + saldo);
        System.out.println("Estado: " + estado);
    }
}