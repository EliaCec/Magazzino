package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.SedutaSedia;

public class RepartoSedutaSedia extends RepartoSemilavoratiAbs {

	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoSedutaSedia() {
		super(CAPACITA, NomiReparti.REPARTO_SEDUTA_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SedutaSedia();
	}
}
