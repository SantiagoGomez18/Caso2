package test;

public class Referencia {
  private String matriz;
  private int paginaVirtual;
  private int desplazamiento;
  private String accion;

  public Referencia(String matriz, int pv, int desp, String accion) {
      this.matriz = matriz;
      this.paginaVirtual = pv;
      this.desplazamiento = desp;
      this.accion = accion;
  }
}
