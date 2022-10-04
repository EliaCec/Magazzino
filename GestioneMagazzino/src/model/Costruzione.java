package model;

import java.util.Date;

public interface Costruzione {

	/* Metodo che ritorna l'operaio che ha eseguito la costruzione */
	public Operaio getOperaio();
	
	/* Metodo che prodotto finito � stato costruito */
	public ProdottoFinito getProdotto();
	
	/* Metodo che ritorna quando � stata fatta la costruzione */
	public Date getData();
	
}
