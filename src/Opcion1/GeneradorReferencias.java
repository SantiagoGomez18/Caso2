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

    public String generarReferencia(){
        ;
        //Recorrer la imagen aplicando los dos filtros de Sobel
        for (int i=1; i<imagen.alto-1; i++){
            for (int j=1; j < imagen.ancho-1; j++){
                int gradXRed = 0, gradXGreen = 0, gradXBlue = 0;
                int gradYRed = 0, gradYGreen = 0, gradYBlue = 0;
                // Aplicar las máscaras Sobel X y Y
                for (int ki = -1; ki <= 1; ki++) {
                    for (int kj = -1; kj <= 1; kj++) {
                        int red = imagen.imagen[i+ki][j+kj][0];
                        int green = imagen.imagen[i+ki][j+kj][1];
                        int blue = imagen.imagen[i+ki][j+kj][2];
                        for(int s=0; s<3; s++){
                            int fila = i+ki;
                            int columna = j+kj;
                            int off = (fila * imagen.ancho * 3) + (columna * 3) + s;
                            int pagina = off / tamanoPagina;
                            int desplazamiento = off % tamanoPagina;

                            if (s == 0) {
                                System.out.println("IMAGEN[" + i + "][" + j + "].r,"  + pagina + "," + desplazamiento + ",R");      
                                gradXRed += red * SOBEL_X[ki + 1][kj + 1];
                                gradYRed += red * SOBEL_Y[ki + 1][kj + 1];
                            } else if (s == 1) {
                                System.out.println("IMAGEN[" + i + "][" + j + "].g,"  + pagina + "," + desplazamiento + ",R");
                                gradXGreen += green * SOBEL_X[ki + 1][kj + 1];
                                gradYGreen += green * SOBEL_Y[ki + 1][kj + 1];
                            } else if (s == 2) {
                                System.out.println("IMAGEN[" + i + "][" + j + "].b,"  + pagina + "," + desplazamiento + ",R");
                                gradXBlue += blue * SOBEL_X[ki + 1][kj + 1];
                                gradYBlue += blue * SOBEL_Y[ki + 1][kj + 1];
                            }

                        }
                    }
                }
                // Calcular la magnitud del gradiente
                int red = Math.min(Math.max((int)Math.sqrt(gradXRed * gradXRed+
                gradYRed * gradYRed),0),255);
                int green = Math.min(Math.max((int)Math.sqrt(gradXGreen * gradXGreen +
                gradYGreen* gradYGreen),0),255);
                int blue = Math.min(Math.max((int) Math.sqrt(gradXBlue * gradXBlue +
                gradYBlue * gradYBlue),0),255);
                // Crear el nuevo valor RGB
                imagenOut.imagen[i][j][0] = (byte)red;
                imagenOut.imagen[i][j][1] = (byte)green;
                imagenOut.imagen[i][j][2] = (byte)blue;
                System.out.println("RTA ["+i+"]["+j+"], r");
                System.out.println("RTA ["+i+"]["+j+"], g");
                System.out.println("RTA ["+i+"]["+j+"], b");
            }
        }
        return "a";
    }

    
}
