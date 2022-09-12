package model;

import java.util.LinkedList;

public interface GestioneResponsabili {
	
	/* Metodo che serve per estrarre una lista di semilavorati venduti da un responsabile.
	 * Restituisce la relativa lista di semilavorati */
	public LinkedList<Semilavorato> depositoPerResponsabile(Responsabile responsabile);
	
	/* Metodo che serve per estrarre i prodotti finiti venduti da un responsabile.
	 * Restituisce la relativa lista di prodotti finiti */
    public LinkedList<ProdottoFinito> venditaPerResponsabile(Responsabile responsabile);
    
    /* Metodo che restituisce il numero di prodotti finiti venduti per tipologia */
    public int venditaPerTipologia(ProdottoFinito pf);
    
    /* Metodo che restituisce il numero di semilavorati depositati in base al semilavorato
     * dato in input*/
    public int depositoPerSemilavorato(Semilavorato sl);
}
