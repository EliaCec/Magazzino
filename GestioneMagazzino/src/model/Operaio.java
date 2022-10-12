package model;

import java.util.Date;
import java.util.HashMap;
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
	public boolean costruisciProdottiFiniti(HashMap<ProdottoFinito, Integer> pf, Operaio operaio, Date giorno);
	
	/* Metodo che restituisce il numero totale di prodotti finiti costruiti */
	public int costruzioniPerProdottoFinito(ProdottoFinito pf);
	
	/* Metodo che restituisce il numero totale dei semilavorati prelevati */
	public int prelievoPerSemilavorato(Semilavorato sl);
	
	/* Metodo che restituisce il numero di prodotti finiti ancora costruibili 
	 * basandosi sui semilavorati rimanenti in magazzino */
	public int calcoloProdottiFinitiCostruibili(ProdottoFinito pf);
	
}
