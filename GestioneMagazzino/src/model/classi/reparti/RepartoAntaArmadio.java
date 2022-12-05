package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.AntaArmadio;

public class RepartoAntaArmadio extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 100;
	
	// costruttore
	public RepartoAntaArmadio() {
		super(CAPACITA, NomiReparti.REPARTO_ANTA_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		return new AntaArmadio();
	}
	
}
