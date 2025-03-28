package test;
import java.util.List;

public class Lector implements Runnable {
    private MarcoReferencias referencias;
    private ProcesadorReferencias procesador;

    public Lector(MarcoReferencias refs, ProcesadorReferencias proc) {
        this.referencias = refs;
        this.procesador = proc;
    }

    @Override
    public void run() {
        List<Referencia> lista = referencias.getReferencias();
        for (Referencia ref : lista) {
            // Verificar si la página está en RAM (simplificado)
            procesador.incrementarHits(); // Placeholder
        }
    }
}
