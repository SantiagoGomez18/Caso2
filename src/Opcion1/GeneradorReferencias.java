package Opcion1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneradorReferencias {
    private Imagen imagenIn;
    private Imagen imagenOut;

    public GeneradorReferencias(Imagen imagenIn, Imagen imagenOut) {
        this.imagenIn = imagenIn;
        this.imagenOut = imagenOut;
    }

    public void generateReferences(int pageSize, String outputFile) throws IOException {
        int alto = imagenIn.alto;
        int ancho = imagenIn.ancho;
        long sizeImagen = (long) alto * ancho * 3; // Size of imagenIn or imagenOut in bytes
        long startImagenIn = 0;
        long startSOBEL_X = startImagenIn + sizeImagen;
        long startSOBEL_Y = startSOBEL_X + 36; // 3x3 int array = 9 * 4 bytes
        long startImagenOut = startSOBEL_Y + 36;
        long totalSize = startImagenOut + sizeImagen;
        long NR = (alto - 2) * (ancho - 2) * 84L; // 84 references per pixel
        int NP = (int) ((totalSize + pageSize - 1) / pageSize); // Ceiling division

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            // Write metadata
            writer.println("TP=" + pageSize);
            writer.println("NF=" + alto);
            writer.println("NC=" + ancho);
            writer.println("NR=" + NR);
            writer.println("NP=" + NP);

            // Simulate memory accesses
            for (int i = 1; i < alto - 1; i++) {
                for (int j = 1; j < ancho - 1; j++) {
                    for (int ki = -1; ki <= 1; ki++) {
                        for (int kj = -1; kj <= 1; kj++) {
                            // Reads from imagenIn
                            int row = i + ki;
                            int col = j + kj;
                            for (int c = 0; c < 3; c++) {
                                long addr = startImagenIn + (long) row * ancho * 3 + col * 3 + c;
                                int page = (int) (addr / pageSize);
                                int offset = (int) (addr % pageSize);
                                String channel = (c == 0) ? "r" : (c == 1) ? "g" : "b";
                                writer.println("Imagen[" + row + "][" + col + "]." + channel + ", " +
                                               page + ", " + offset + ", R");
                            }
                            // Reads from SOBEL_X (3 times per ki, kj)
                            int sobelRow = ki + 1;
                            int sobelCol = kj + 1;
                            long addrX = startSOBEL_X + (long) (sobelRow * 3 + sobelCol) * 4;
                            int pageX = (int) (addrX / pageSize);
                            int offsetX = (int) (addrX % pageSize);
                            for (int k = 0; k < 3; k++) {
                                writer.println("SOBEL_X[" + sobelRow + "][" + sobelCol + "], " +
                                               pageX + ", " + offsetX + ", R");
                            }
                            // Reads from SOBEL_Y (3 times per ki, kj)
                            long addrY = startSOBEL_Y + (long) (sobelRow * 3 + sobelCol) * 4;
                            int pageY = (int) (addrY / pageSize);
                            int offsetY = (int) (addrY % pageSize);
                            for (int k = 0; k < 3; k++) {
                                writer.println("SOBEL_Y[" + sobelRow + "][" + sobelCol + "], " +
                                               pageY + ", " + offsetY + ", R");
                            }
                        }
                    }
                    // Writes to imagenOut
                    for (int c = 0; c < 3; c++) {
                        long addr = startImagenOut + (long) i * ancho * 3 + j * 3 + c;
                        int page = (int) (addr / pageSize);
                        int offset = (int) (addr % pageSize);
                        String channel = (c == 0) ? "r" : (c == 1) ? "g" : "b";
                        writer.println("Rta[" + i + "][" + j + "]." + channel + ", " +
                                       page + ", " + offset + ", W");
                    }
                }
            }
        }
    }
}
