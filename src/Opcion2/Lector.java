package Opcion2;

public class Lector extends Thread{
    private MarcoPaginas marcoPaginas;
    private MarcoReferencias marcoReferencias;

    public Lector(MarcoPaginas marcoPaginas, MarcoReferencias marcoReferencias) {
        this.marcoPaginas = marcoPaginas;
        this.marcoReferencias = marcoReferencias;
    }
}
