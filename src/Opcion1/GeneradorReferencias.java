package Opcion1;
import Opcion1.Imagen;
import Opcion1.FiltroSobel;

public class GeneradorReferencias {
    Imagen imagen;
    FiltroSobel filtro;
    int tamanoPagina;
    String nombreArchivo;

    public GeneradorReferencias(int tamanoPagina, String nombreArchivo) {
        this.imagen = new Imagen(nombreArchivo);
        this.filtro = new FiltroSobel(imagen, new Imagen("output.bmp")); 
        this.tamanoPagina = tamanoPagina;
    }

    public String generarReferencia(){
        int tp = tamanoPagina;
        int alto = imagen.alto;
        int ancho = imagen.ancho;

        return "A";  
    }
}
