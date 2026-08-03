import java.util.Scanner;

public class DesafiosLogicos {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        System.out.println("====================================");
        System.out.println("Estudiante: Gerson Ezequiel López Enriquez");
        System.out.println("Carné: 9941-25-22144");
        System.out.println("Ingenieria en Sistemas");
        System.out.println("Semana 3 — Condiciones y ciclos");
        System.out.println("====================================");

        do {

            System.out.println("\n========= DESAFÍOS LÓGICOS =========");
            System.out.println("1. Generar una secuencia");
            System.out.println("2. Realizar un conteo regresivo");
            System.out.println("3. Analizar números");
            System.out.println("4. Dibujar una pirámide");
            System.out.println("5. Validar palabra secreta");
            System.out.println("6. Salir");
            System.out.print("\nSeleccione una opción: ");


            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Error: debe ingresar una opción numérica.");
                scanner.nextLine();
                opcion = 0;
            }


            switch (opcion) {


                case 1:

                    int numeroInicial;
                    int numeroFinal;
                    int incremento;

                    System.out.println("\n--- GENERAR UNA SECUENCIA ---");

                    System.out.print("Número inicial: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.nextLine();
                        System.out.print("Número inicial: ");
                    }
                    numeroInicial = scanner.nextInt();

                    System.out.print("Número final: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.nextLine();
                        System.out.print("Número final: ");
                    }
                    numeroFinal = scanner.nextInt();

                    System.out.print("Incremento: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.nextLine();
                        System.out.print("Incremento: ");
                    }
                    incremento = scanner.nextInt();
                    scanner.nextLine();

                    if (incremento <= 0) {
                        System.out.println(
                                "Datos incorrectos: el incremento debe ser mayor que cero."
                        );
                    } else if (numeroFinal <= numeroInicial) {
                        System.out.println(
                                "Datos incorrectos: el número final debe ser mayor que el inicial."
                        );
                    } else {
                        System.out.println("\nResultado:");

                        for (int i = numeroInicial; i <= numeroFinal; i += incremento) {
                            System.out.print(i + " ");
                        }

                        System.out.println();
                    }

                    break;


                case 2:

                    int numeroConteo = 0;
                    boolean numeroValido = false;

                    System.out.println("\n--- CONTEO REGRESIVO ---");

                    while (!numeroValido) {

                        System.out.print("Ingrese un número entre 10 y 50: ");

                        if (scanner.hasNextInt()) {
                            numeroConteo = scanner.nextInt();
                            scanner.nextLine();

                            if (numeroConteo >= 10 && numeroConteo <= 50) {
                                numeroValido = true;
                            } else {
                                System.out.println(
                                        "Número incorrecto. Debe estar entre 10 y 50."
                                );
                            }

                        } else {
                            System.out.println("Error: debe ingresar un número entero.");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("\nResultado:");


                    while (numeroConteo >= 0) {
                        System.out.print(numeroConteo + " ");
                        numeroConteo--;
                    }

                    System.out.println("\n¡Despegue!");

                    break;

                case 3:

                    int numero;
                    int positivos = 0;
                    int negativos = 0;
                    int numerosIgnorados = 0;
                    int suma = 0;

                    System.out.println("\n--- ANALIZAR NÚMEROS ---");
                    System.out.println("Ingrese números enteros.");
                    System.out.println("Escriba 0 para finalizar.");


                    while (true) {

                        System.out.print("Ingrese un número: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println(
                                    "Error: solamente se permiten números enteros."
                            );
                            scanner.nextLine();
                            continue;
                        }

                        numero = scanner.nextInt();
                        scanner.nextLine();


                        if (numero == 0) {
                            break;
                        }


                        if (numero > 0) {
                            positivos++;
                        } else {
                            negativos++;
                        }

                        if (numero % 5 == 0) {
                            numerosIgnorados++;
                            System.out.println(
                                    "El número " + numero + " fue ignorado."
                            );
                            continue;
                        }


                        suma += numero;
                    }

                    System.out.println("\nResultado:");
                    System.out.println("Positivos: " + positivos);
                    System.out.println("Negativos: " + negativos);
                    System.out.println("Suma válida: " + suma);
                    System.out.println(
                            "Números ignorados: " + numerosIgnorados
                    );

                    break;


                case 4:

                    int altura = 0;
                    boolean alturaValida = false;

                    System.out.println("\n--- DIBUJAR UNA PIRÁMIDE ---");


                    while (!alturaValida) {

                        System.out.print("Ingrese la altura entre 3 y 10: ");

                        if (scanner.hasNextInt()) {
                            altura = scanner.nextInt();
                            scanner.nextLine();

                            if (altura >= 3 && altura <= 10) {
                                alturaValida = true;
                            } else {
                                System.out.println(
                                        "Altura incorrecta. Debe estar entre 3 y 10."
                                );
                            }

                        } else {
                            System.out.println(
                                    "Error: debe ingresar una altura numérica."
                            );
                            scanner.nextLine();
                        }
                    }

                    System.out.println("\nResultado:");

                    for (int fila = 1; fila <= altura; fila++) {


                        for (int espacio = 1; espacio <= altura - fila; espacio++) {
                            System.out.print(" ");
                        }

                        for (int asterisco = 1;
                             asterisco <= (2 * fila - 1);
                             asterisco++) {

                            System.out.print("*");
                        }

                        System.out.println();
                    }

                    break;


                case 5:

                    String palabra;

                    System.out.println("\n--- VALIDAR PALABRA SECRETA ---");

                    do {

                        System.out.print("Ingrese la palabra secreta: ");
                        palabra = scanner.nextLine().trim();

                        if (palabra.equalsIgnoreCase("Guatemala")) {
                            System.out.println("Palabra correcta.");
                        } else {
                            System.out.println(
                                    "Palabra incorrecta. Intente nuevamente.\n"
                            );
                        }

                    } while (!palabra.equalsIgnoreCase("Guatemala"));

                    break;


                case 6:

                    System.out.println(
                            "\nPrograma finalizado correctamente."
                    );

                    break;


                default:

                    System.out.println(
                            "\nOpción inexistente. Seleccione una opción del 1 al 6."
                    );

                    break;
            }

        } while (opcion != 6);

        scanner.close();
    }
}