package test;
import java.io.*;

public class Imagen {
    private byte[] header = new byte[54];
    private byte[][][] imagen; // [alto][ancho][RGB]
    private int alto;
    private int ancho;

    public Imagen(String filePath) {
        // Placeholder para cargar BMP
        try (FileInputStream fis = new FileInputStream(filePath)) {
            fis.read(header);
            ancho = byteArrayToInt(header, 18);
            alto = byteArrayToInt(header, 22);
            imagen = new byte[alto][ancho][3];
            // Leer datos de pixeles (implementar según formato BMP)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAlto() { return alto; }
    public int getAncho() { return ancho; }
    public byte[] getPixel(int i, int j) { return imagen[i][j]; }
    public void setPixel(int i, int j, byte[] rgb) { imagen[i][j] = rgb; }

    private int byteArrayToInt(byte[] b, int offset) {
        return (b[offset + 3] & 0xFF) << 24 | (b[offset + 2] & 0xFF) << 16 |
               (b[offset + 1] & 0xFF) << 8 | (b[offset] & 0xFF);
    }
}
