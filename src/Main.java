public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione que opción desea: 1 para generar referencias o 2 para calcular datos:");
        int opcionSeleccionada = scanner.nextInt();
        scanner.nextLine(); 
    
        if (opcionSeleccionada == 1) {
            System.out.println("Seleccione el tamaño de la imagen: ");
            int tamanio = scanner.nextInt();
            scanner.nextLine(); 
            System.out.println("Seleccione el nombre de la imagen: ");
            String nombreImagen = scanner.nextLine();
            GeneradorReferencias generadorReferencias = new GeneradorReferencias(tamanio, nombreImagen);
            generadorReferencias.generarReferencia();
        } else {
            System.out.println("Seleccione el número de marcos de página: ");
            int numeroMarcosPagina = scanner.nextInt();
            scanner.nextLine(); 
            System.out.println("Escriba el nombre del archivo de referencias: ");
            String archivoReferencias = scanner.nextLine();
            ProcesadorReferencias procesadorReferencias = new ProcesadorReferencias(numeroMarcosPagina, archivoReferencias);
            // Invoca el método correspondiente para procesar las referencias
        }
        scanner.close();
    }
}
