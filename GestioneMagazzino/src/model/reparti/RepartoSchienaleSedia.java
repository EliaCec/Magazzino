package model.reparti;

import model.semilavorati.SchienaleSedia;
import model.Giacenza;

public class RepartoSchienaleSedia extends RepartoAbs {
	
	// costruttore
	public RepartoSchienaleSedia() {
		super(200, NomiReparti.REPARTO_SCHIENALE_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SchienaleSedia();
	}
}
