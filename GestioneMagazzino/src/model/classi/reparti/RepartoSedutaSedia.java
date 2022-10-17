package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.SedutaSedia;

public class RepartoSedutaSedia extends RepartoSemilavoratiAbs {

	// costruttore
	public RepartoSedutaSedia() {
		super(200, NomiReparti.REPARTO_SEDUTA_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SedutaSedia();
	}
}
