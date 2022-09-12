package model;

import java.util.HashMap;
import java.util.LinkedList;

public interface Responsabile {
	/* Metodo che restituisce il nome e il cognome del responsabile */
	public String getNomeCognome();   
	
	/* Meteodo che restituisce l'id del responsabile */
	public int getId();
	
	/* Metodo che serve per estrarre tutti i prodotti finiti venduti dal responsabile.
	 * Restituisce la relativa lista di prodotti finiti */
	public LinkedList<ProdottoFinito> getProdottiVenduti();
	
	/* Metodo che serve per estrarre tutti i semilavorati depositati dal responsabile.
	 * Restituisce la relativa lista dei semilavorati */
	public LinkedList<Semilavorato> getSemilavoratiDepositati();
	
	/* Metodo usato per la vendita dei prodotti finiti da parte del responsabile.
	 * Prende in input la mappa dei prodotti finiti da vendere, il responsabile che le ha vendute
	 * e la giornata.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public boolean vendiProdottiFiniti(HashMap<ProdottoFinito, Integer> prodottofinito,
			                           Responsabile responsabile,
			                           int giorno);
	
	/* Metodo usato per il deposito dei semilavorati da parte del responsabile.
	 * Prende in input la mappa dei semilavorati depositati, il responsabile che le ha vendute
	 * e la giornata.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public boolean depositaSemilavorati(HashMap<Semilavorato, Integer> semilavorato,
			                            Responsabile responsabile,
			                            int giorno);
}
