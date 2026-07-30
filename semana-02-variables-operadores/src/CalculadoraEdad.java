import java.util.Scanner;

public class CalculadoraEdad {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        String nombreCompleto;
        int anioNacimiento;
        int anioActual;
        int edadAproximada;
        int edadEnMeses;
        boolean esMayorDeEdad;

        System.out.print("Ingrese su nombre completo: ");
        nombreCompleto = teclado.nextLine();

        System.out.print("Ingrese su año de nacimiento: ");
        anioNacimiento = teclado.nextInt();

        System.out.print("Ingrese el año actual: ");
        anioActual = teclado.nextInt();

        edadAproximada = anioActual - anioNacimiento;

        edadEnMeses = edadAproximada * 12;

        esMayorDeEdad = edadAproximada >= 18;

        System.out.println();
        System.out.println("----- RESULTADO -----");
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Edad aproximada: " + edadAproximada + " años");
        System.out.println("Edad aproximada en meses: " + edadEnMeses + " meses");
        System.out.println("¿Es mayor de edad?: " + esMayorDeEdad);

        teclado.close();
    }
}