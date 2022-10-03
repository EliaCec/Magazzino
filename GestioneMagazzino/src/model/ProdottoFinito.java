package model;

import java.util.HashMap;

public interface ProdottoFinito extends Giacenza{
	
	/* Metodo che restituisce la mappa dei semilavorati necessari alla costruzione
	 * di un prodotto finito. */
	public HashMap<Semilavorato, Integer> getComponenti();
}
