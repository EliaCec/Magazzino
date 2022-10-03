package model;

import java.util.HashMap;
import java.util.LinkedList;

public interface ProdottoFinito extends Giacenza{
	
	/* Metodo che restituisce la mappa dei semilavorati necessari alla costruzione
	 * di un prodotto finito. */
	public HashMap<String, Integer> getComponenti();
	
	/* Metodo che serve per inserire i componenti dei vari prodotti finiti */
	public void inserisciComponenti();
}
