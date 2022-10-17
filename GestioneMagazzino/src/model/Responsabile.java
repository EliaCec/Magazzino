package model;

import java.util.Date;
import java.util.List;

public interface Responsabile extends Dipendente{
	
	/* Metodo che serve per estrarre tutti i prodotti finiti venduti dal responsabile.
	 * Restituisce la relativa lista di prodotti finiti */
	public List<Vendita> getProdottiVenduti();
	
	/* Metodo che serve per estrarre tutti i semilavorati depositati dal responsabile.
	 * Restituisce la relativa lista dei semilavorati */
	public List<Deposito> getSemilavoratiDepositati();
	
	/* Metodo usato per la vendita dei prodotti finiti da parte del responsabile.
	 * Prende in input il reparto dei prodotti finiti da vendere , il numero , il responsabile che le ha vendute
	 * e la giornata.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public void vendiProdottiFiniti(RepartoProdottiFiniti reparto, 
									   int n,
			                           Responsabile responsabile, 
			                           Date data);
	
	/* Metodo usato per il deposito dei semilavorati da parte del responsabile.
	 * Prende in input il reparto in cui verranno depositati i semilavorati,il numero di semilavorati, il responsabile che le ha vendute
	 * e la giornata.
	 * Restituisce true se la costruzione è stata effettuata con successo */
	public void depositaSemilavorati(RepartoSemilavorati reparto,
										int n,
			                            Responsabile responsabile,
			                            Date data);
	
	/* Metodo che restituisce il numero di prodotti finiti venduti per tipologia */
    public int venditaPerTipologia(String pf);
    
    /* Metodo che restituisce il numero di semilavorati depositati in base al semilavorato
     * dato in input*/
    public int depositoPerSemilavorato(String sl);
}
