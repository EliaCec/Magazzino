package model.reparti;

import model.Giacenza;
import model.semilavorati.GambaScrivania;

public class RepartoGambaScrivania extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoGambaScrivania() {
		super(300, NomiReparti.REPARTO_GAMBA_SCRIVANIA);
	}

	protected Giacenza creaGiacenza() {
		return new GambaScrivania();
	}
	
}
