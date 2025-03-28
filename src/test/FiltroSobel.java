package test;
public class FiltroSobel {
  private Imagen imagenIn;
  private Imagen imagenOut;
  private int[][] filtroX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
  private int[][] filtroY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};

  public FiltroSobel(Imagen imagenIn, Imagen imagenOut) {
      this.imagenIn = imagenIn;
      this.imagenOut = imagenOut;
  }

  public void applySobel() {
      int alto = imagenIn.getAlto();
      int ancho = imagenIn.getAncho();
      for (int i = 1; i < alto - 1; i++) {
          for (int j = 1; j < ancho - 1; j++) {
              // Simular accesos a pixeles y filtros
              // En Opción 1: registrar referencias en lugar de calcular
              // En implementación real: aplicar convolución
          }
      }
  }
}
