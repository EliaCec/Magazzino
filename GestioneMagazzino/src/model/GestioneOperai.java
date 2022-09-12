package model;

import java.util.LinkedList;

public interface GestioneOperai {

	/* Metodo che serve per estrarre tutti i prodotti finiti costruiti da un singolo operaio.
	 * Restituisce la relativa lista di prodotti finiti */
	public LinkedList<ProdottoFinito> costruzioniPerOperaio(Operaio operaio);
	
	/* Metodo che serve per estrarre tutti i semilavorati utilizzati da un singolo operaio.
	 * Restituisce la relativa lista di semilavorati */
	public LinkedList<Semilavorato> prelievoPerOperaio(Operaio operaio);
}
