package model;

import java.util.Date;
import java.util.List;

public interface Responsabile {
	/* Metodo che restituisce il nome e il cognome del responsabile */
	public String getNomeCognome();   
	
	/* Meteodo che restituisce l'id del responsabile */
	public int getId();
	
	/* Metodo che serve per estrarre tutti i prodotti finiti venduti dal responsabile.
	 * Restituisce la relativa lista di prodotti finiti */
	public List<ProdottoFinito> getProdottiVenduti();
	
	/* Metodo che serve per estrarre tutti i semilavorati depositati dal responsabile.
	 * Restituisce la relativa lista dei semilavorati */
	public List<Semilavorato> getSemilavoratiDepositati();
	
	/* Metodo usato per la vendita dei prodotti finiti da parte del responsabile.
	 * Prende in input il reparto dei prodotti finiti da vendere , il numero , il responsabile che le ha vendute
	 * e la giornata.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public boolean vendiProdottiFiniti(Reparto reparto, 
									   int n,
			                           Responsabile responsabile, 
			                           Date data);
	
	/* Metodo usato per il deposito dei semilavorati da parte del responsabile.
	 * Prende in input il reparto in cui verranno depositati i semilavorati,il numero di semilavorati, il responsabile che le ha vendute
	 * e la giornata.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public boolean depositaSemilavorati(Reparto reparto,
										int n,
			                            Responsabile responsabile,
			                            Date data);
}
