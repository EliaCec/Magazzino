package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.GambaSedia;

public class RepartoGambaSedia extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 350;
	
	// costruttore
	public RepartoGambaSedia() {
		super(CAPACITA,  NomiReparti.REPARTO_GAMBA_SEDIA);
	}

	protected Giacenza creaGiacenza() {
		return new GambaSedia();
	}
	
}
