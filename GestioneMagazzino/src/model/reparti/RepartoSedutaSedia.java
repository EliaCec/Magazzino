package model.reparti;

import model.semilavorati.SedutaSedia;
import model.Giacenza;

public class RepartoSedutaSedia extends RepartoAbs {

	// costruttore
	public RepartoSedutaSedia() {
		super(200, NomiReparti.REPARTO_SEDUTA_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SedutaSedia();
	}
}
