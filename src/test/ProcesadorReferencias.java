package test;
public class ProcesadorReferencias {
  private MarcoPagina[] marcos;
  private int numMarcos;
  private MarcoReferencias archivoReferencias;
  private int numHits = 0;
  private int numFallas = 0;

  public ProcesadorReferencias(int numMarcos, String archivo) {
      this.numMarcos = numMarcos;
      this.marcos = new MarcoPagina[numMarcos];
      for (int i = 0; i < numMarcos; i++) {
          marcos[i] = new MarcoPagina();
      }
      this.archivoReferencias = new MarcoReferencias(archivo);
  }

  public void procesarReferencias() {
      Thread lector = new Thread(new Lector(archivoReferencias, this));
      Thread actualizador = new Thread(new Actualizador(marcos));
      actualizador.start();
      lector.start();
      try {
          lector.join();
          actualizador.join();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

  public void calcularTiempos() {
      long tiempoHit = 50; // ns
      long tiempoFalla = 10000000; // 10 ms en ns
      long tiempoTotal = (numHits * tiempoHit) + (numFallas * tiempoFalla);
      System.out.println("Tiempo total: " + tiempoTotal + " ns");
  }

  public void generarInforme() {
      System.out.println("Hits: " + numHits);
      System.out.println("Fallas: " + numFallas);
      System.out.println("Porcentaje Hits: " + (numHits * 100.0 / (numHits + numFallas)) + "%");
  }

  public synchronized void incrementarHits() { numHits++; }
  public synchronized void incrementarFallas() { numFallas++; }
}
