package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.AntaArmadio;

public class RepartoAntaArmadio extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoAntaArmadio() {
		super(100, NomiReparti.REPARTO_ANTA_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		return new AntaArmadio();
	}
	
}