package model;

import java.util.Date;

public interface Vendita {
	
	/* Metodo che restituisce il prodotto finito venduto */
	public ProdottoFinito getProdottoFinito();
	
	/* Metodo che restituisce il responsabile che ha venduto il prodotto*/
	public Responsabile getResponsabile();
	
	/* Metodo che restituisce quando è stato venduto */
	public Date getData();

}
