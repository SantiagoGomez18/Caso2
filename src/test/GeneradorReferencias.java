package test;

import java.io.*;

public class GeneradorReferencias {
    private FiltroSobel filtro;
    private Imagen imagen;
    private int tamanoPagina;
    private String nombreArchivo;

    public GeneradorReferencias(int tamanoPagina, String nombreArchivo) {
        this.tamanoPagina = tamanoPagina;
        this.nombreArchivo = nombreArchivo;
        this.imagen = new Imagen(nombreArchivo);
        this.filtro = new FiltroSobel(imagen, new Imagen("output.bmp")); // Imagen de salida
    }

    public void generarReferencias() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("referencias.txt"))) {
            // Escribir metadatos
            writer.println("TP=" + tamanoPagina);
            writer.println("NF=" + imagen.getAlto());
            writer.println("NC=" + imagen.getAncho());
            int NR = calcularNumeroReferencias();
            int NP = calcularPaginasVirtuales();
            writer.println("NR=" + NR);
            writer.println("NP=" + NP);

            // Simular accesos a memoria (esto es un placeholder)
            // Debes rastrear accesos a imagen, filtroX, filtroY y respuesta
            filtro.applySobel(); // Simulación para generar referencias
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calcularNumeroReferencias() {
        // Calcular basado en accesos a matrices durante applySobel
        return imagen.getAlto() * imagen.getAncho() * 9; // Ejemplo aproximado
    }

    private int calcularPaginasVirtuales() {
        // Calcular páginas necesarias para todas las matrices
        int tamanoTotal = (imagen.getAlto() * imagen.getAncho() * 3) * 4; // Aproximación
        return (int) Math.ceil((double) tamanoTotal / tamanoPagina);
    }
}
