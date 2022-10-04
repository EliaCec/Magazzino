package model;

import java.util.Date;

public interface Deposita {
	
	/* Metodo che restituisce il semilavorato depositato */
	public Semilavorato getSemilavorato();
	
	/* Metodo che restituisce il responsabile che ha depositato il semilavorato*/
	public Responsabile getResponsabile();
	
	/* Metodo che restituisce quando è stato depositato */
	public Date getData();

}
