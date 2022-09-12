package model;

import java.util.Date;
import java.util.LinkedList;

public interface Magazzino {
	/* Metodo che restituisce il numero totale dei reparti presenti nel magazzino */
	public int getNumeroReparti(); 
	
	/* Metodo che serve per estrarre tutti i prodotti finiti che sono stati venduti in
	 * un determinato giorno nell'intero magazzino.
	 * Restituisce la relativa lista di prodotti finiti */
	public LinkedList<ProdottoFinito> storicoVenditeGiornaliero(Date giorno);
	
	/* Metodo che serve per estrarre tutti i prodotti finiti che sono stati costruiti
	 * in un determinato giorno nell'intero magazzino.
	 * Restituisce la relativa lista di prodotti finiti */
	public LinkedList<ProdottoFinito> storicoCostruzioniGiornaliero(Date giorno);
	
	/* Metodo che serve per estrarre tutti i semilavorati consumati in un determinato giorno
	 * nell'intero magazzino.
	 * Restituisce la relativa lista di semilavorati */
	public LinkedList<Semilavorato> storicoSemilavoratiUsatiGiornaliero(Date giorno);
	
	/* Metodo che restituice i responsabili che stanno lavorando in questo momento nel magazzino */
	public LinkedList<Responsabile> getResponsabiliAttivi();
	
	/* Metodo che restituisce gli operai che stanno lavorando in questo momento nel magazzino */
	public LinkedList<Operaio> getOperaiAttivi();
	
	/* Metodo che serve per effettuare il cambio turno degli impiegati del magazzino.
	 * Restituisce true se il cambio di turno è avvenuto con successo, false altrimenti */
	public boolean cambioTurno(LinkedList<Operaio> operai, 
							   LinkedList<Responsabile> responsabili,
							   Date giorno);
}
