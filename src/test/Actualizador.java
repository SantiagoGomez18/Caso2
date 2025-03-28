package test;
public class Actualizador implements Runnable {
  private MarcoPagina[] marcos;

  public Actualizador(MarcoPagina[] marcos) {
      this.marcos = marcos;
  }

  @Override
  public void run() {
      while (true) {
          try {
              Thread.sleep(1);
              for (MarcoPagina marco : marcos) {
                  marco.setReferenced(false); // Resetear bit según NRU
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }

  public synchronized void reemplazarPagina(Pagina pagina) {
      // Implementar NRU para reemplazo
      int victima = 0; // Simplificación
      marcos[victima].setPagina(pagina);
  }
}
