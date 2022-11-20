package model;

import java.util.Date;
import java.util.List;

public interface Operaio extends Dipendente {

	/* Metodo che serve per estrarre tutti i prodotti finiti costruiti.
	 * Restituisce la relativa lista di prodotti finiti */ 
	public List<Costruzione> getProdottiCostruiti();
	
	/* Metodo che serve per estrarre tutti i semilavorati utilizzati.
	 * Restituisce la relativa lista di semilavorati */ 
	public List<Prelievo> getSemilavoratiPrelevati();
	
	/* Metodo che serve per costruire un prodotto finito (pf).
	 * Prende in input la mappa dei prodotti finiti da costruire, l'operaio responsabile alla costruzione e la giornata.
	 * Il metodo preleva anche i semilavorati necessari alla costruzione del prodotto finito.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public void costruisciProdottiFiniti(RepartoProdottiFiniti rep, int n, Date giorno);
	
	/* Metodo che restituisce il numero totale di prodotti finiti costruiti */
	public int costruzioniPerProdottoFinito(String pf);
	
	/* Metodo che restituisce il numero totale dei semilavorati prelevati */
	public int prelievoPerSemilavorato(String sl);
	
	/* Metodo che restituisce il numero di prodotti finiti ancora costruibili 
	 * basandosi sui semilavorati rimanenti in magazzino */
	public int calcoloProdottiFinitiCostruibili(RepartoProdottiFiniti rep);	
}
