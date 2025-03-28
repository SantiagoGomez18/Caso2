package Opcion2;

public class Actualizador extends Thread{
    private MarcoReferencias marcoReferencias;

    public Actualizador(MarcoReferencias marcoReferencias) {
        this.marcoReferencias = marcoReferencias;
    }
}
