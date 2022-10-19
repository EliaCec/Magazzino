package model;

import java.util.Date;
import java.util.List;

public interface Magazzino {
	/* Metodo che restituisce il numero totale dei reparti presenti nel magazzino */
	public int getNumeroReparti(); 
	
	/* Metodo che serve per estrarre tutti i prodotti finiti che sono stati venduti in
	 * un determinato giorno nell'intero magazzino.
	 * Restituisce la relativa lista di prodotti finiti */
	public List<Vendita> storicoVenditeGiornaliero(Date giorno);
	
	/* Metodo che serve per estrarre tutti i prodotti finiti che sono stati costruiti
	 * in un determinato giorno nell'intero magazzino.
	 * Restituisce la relativa lista di prodotti finiti */
	public List<Costruzione> storicoCostruzioniGiornaliero(Date giorno);
	
	/* Metodo che serve per estrarre tutti i semilavorati consumati in un determinato giorno
	 * nell'intero magazzino.
	 * Restituisce la relativa lista di semilavorati */
	public List<Prelievo> storicoSemilavoratiUsatiGiornaliero(Date giorno);
	
	/* Metodo che restituice i responsabili che stanno lavorando in questo momento nel magazzino */
	public List<Responsabile> getResponsabiliAttivi();
	
	/* Metodo che restituisce gli operai che stanno lavorando in questo momento nel magazzino */
	public List<Operaio> getOperaiAttivi();
	
	/* Metodo che serve per effettuare il cambio turno degli impiegati del magazzino.
	 * Restituisce true se il cambio di turno è avvenuto con successo, false altrimenti */
	public void cambioTurno(List<Operaio> operai, 
							   List<Responsabile> responsabili,
							   Date nuovoTurno);
	
	/* Metodo che serve per aggiungere un nuovo reparto all'interno del magazzino */
	public void creaReparto(RepartoProdottiFiniti reparto);
	
	/* Metodo che serve per assumere un nuovo dipendente (operaio o responsabile) del magazzino */
	public void assumiDipendente(Dipendente dip);
	
	/* Metodo che restituisce gli operai assunti */
	public List<Operaio> getOperaiAssunti();
	
	/* Metodo che restituisce i responsabili assunti*/
	public List<Responsabile> getResponsabiliAssunti();
	
}
