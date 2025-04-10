package Opcion1;
import Opcion1.Imagen;
import Opcion1.FiltroSobel;

public class GeneradorReferencias {
    Imagen imagen;
    Imagen imagenOut;
    int tamanoPagina;
    String nombreArchivo;

    // Sobel Kernels para detección de bordes
    static final int[][] SOBEL_X = {
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}
    };
    static final int[][] SOBEL_Y = {
        {-1, -2, -1},
        { 0, 0, 0},
        { 1, 2, 1}
    };

    public GeneradorReferencias(int tamanoPagina, String nombreArchivo) {
        this.imagen = new Imagen(nombreArchivo);
        this.tamanoPagina = tamanoPagina;
        this.imagenOut = new Imagen("");
    }
    public String generarReferencia() {
        StringBuilder referencias = new StringBuilder();
        referencias.append("TP=").append(tamanoPagina).append("\n");
        referencias.append("NF=").append(imagen.alto).append("\n");
        referencias.append("NC=").append(imagen.ancho).append("\n");
    
        // Recorrer la imagen aplicando los dos filtros de Sobel
        for (int i = 1; i < imagen.alto - 1; i++) {
            for (int j = 1; j < imagen.ancho - 1; j++) {
                int gradXRed = 0, gradXGreen = 0, gradXBlue = 0;
                int gradYRed = 0, gradYGreen = 0, gradYBlue = 0;
    
                // Aplicar las máscaras Sobel X y Y
                for (int ki = -1; ki <= 1; ki++) {
                    for (int kj = -1; kj <= 1; kj++) {
                        int fila = i + ki;
                        int columna = j + kj;
    
                        // Acceder a los valores RGB de la imagen
                        int red = imagen.imagen[fila][columna][0];
                        int green = imagen.imagen[fila][columna][1];
                        int blue = imagen.imagen[fila][columna][2];
    
                        // Generar referencia para cada componente de color
                        for (int s = 0; s < 3; s++) {
                            int os = (fila * imagen.ancho * 3) + (columna * 3) + s;
                            int pagina = os / tamanoPagina;
                            int desplazamiento = os % tamanoPagina;
    
                            String color = "r";
                            if(s==1){
                                color = "g";
                            } else if(s==2){
                                color = "b";
                            }
                            referencias.append("Imagen[").append(i).append("][").append(j).append("].")
                                        .append(color).append(",").append(pagina).append(",")
                                        .append(desplazamiento).append(",R\n");
                        }
    
                        // Calcular dirección de memoria de SOBEL_X y SOBEL_Y (se repetirán 3 veces)
                        int filaSobel = ki + 1;
                        int columnaSobel = kj + 1;
                        int osSobel = (filaSobel * 3 + columnaSobel) * Integer.BYTES;
                        int pagina_sobel = osSobel / tamanoPagina;
                        int desplazamiento_sobel = osSobel % tamanoPagina;
    
                        for (int s = 0; s < 3; s++) {
                            referencias.append("SOBEL_X[").append(filaSobel).append("][").append(columnaSobel)
                                        .append("],").append(pagina_sobel).append(",")
                                        .append(desplazamiento_sobel).append(",R\n");
                        }
    
                        for (int s = 0; s < 3; s++) {
                            referencias.append("SOBEL_Y[").append(filaSobel).append("][").append(columnaSobel)
                                        .append("],").append(pagina_sobel).append(",")
                                        .append(desplazamiento_sobel).append(",R\n");
                        }
    
                        // Aplicar las máscaras de Sobel a cada canal de color
                        int pesoX = SOBEL_X[filaSobel][columnaSobel];
                        int pesoY = SOBEL_Y[filaSobel][columnaSobel];
    
                        gradXRed += red * pesoX;
                        gradXGreen += green * pesoX;
                        gradXBlue += blue * pesoX;
    
                        gradYRed += red * pesoY;
                        gradYGreen += green * pesoY;
                        gradYBlue += blue * pesoY;
                    }
                }
    
                // Calcular la magnitud del gradiente
                int red = Math.min(Math.max((int) Math.sqrt(gradXRed * gradXRed + gradYRed * gradYRed), 0), 255);
                int green = Math.min(Math.max((int) Math.sqrt(gradXGreen * gradXGreen + gradYGreen * gradYGreen), 0), 255);
                int blue = Math.min(Math.max((int) Math.sqrt(gradXBlue * gradXBlue + gradYBlue * gradYBlue), 0), 255);
    
                // Guardar los valores en la imagen de salida
                imagenOut.imagen[i][j][0] = (byte) red;
                imagenOut.imagen[i][j][1] = (byte) green;
                imagenOut.imagen[i][j][2] = (byte) blue;
                
                for(int s = 0; s < 3; s++){
                    
                }
            }
        }
    
        return referencias.toString();
    }
    
    
}
