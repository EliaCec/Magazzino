package model.reparti;

import model.Giacenza;
import model.semilavorati.GambaSedia;

public class RepartoGambaSedia extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoGambaSedia() {
		super(350,  NomiReparti.REPARTO_GAMBA_SEDIA);
	}

	protected Giacenza creaGiacenza() {
		return new GambaSedia();
	}
	
}
