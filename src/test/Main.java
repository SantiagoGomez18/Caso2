package test;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione una opción:");
        System.out.println("1. Generar referencias");
        System.out.println("2. Procesar referencias");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        if (opcion == 1) {
            System.out.println("Ingrese tamaño de página (en bytes):");
            int tamanoPagina = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese nombre del archivo de imagen (BMP):");
            String nombreArchivo = scanner.nextLine();
            GeneradorReferencias generador = new GeneradorReferencias(tamanoPagina, nombreArchivo);
            generador.generarReferencias();
        } else if (opcion == 2) {
            System.out.println("Ingrese número de marcos de página:");
            int numMarcos = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese nombre del archivo de referencias:");
            String archivoReferencias = scanner.nextLine();
            ProcesadorReferencias procesador = new ProcesadorReferencias(numMarcos, archivoReferencias);
            procesador.procesarReferencias();
            procesador.calcularTiempos();
            procesador.generarInforme();
        } else {
            System.out.println("Opción no válida.");
        }
        scanner.close();
    }
}
