package model;

import java.util.Date;

public interface Prelievo {
	

	/* Metodo che ritorna l'operaio che ha eseguito il prelievo */
	public Operaio getOperaio();
	
	/* Metodo che restituisce il semilavorato prelevato */
	public Semilavorato getSemilavorato();
	
	/* Metodo che ritorna quando è stato fatto il prelievo */
	public Date getData();

}
