package Opcion2;

import java.util.ArrayList;

public class MarcoPaginas {
    private int nPaginas;
    private ArrayList<Pagina> paginas;
    private int hits;
    private int misses;

    public MarcoPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
        this.paginas = new ArrayList<>();
        this.hits = 0;
        this.misses = 0;
    }

    //Aca yo haria las funciones que calculan hits y misses


}
