package model.reparti;

import model.prodottiFiniti.Sedia;
import model.Giacenza;


public class RepartoSedia extends RepartoAbs {
	
	// costruttore
	public RepartoSedia() {
		super(200, NomiReparti.REPARTO_SEDIA);
	}

	protected Giacenza creaGiacenza() {
		return new Sedia();
	}
}
