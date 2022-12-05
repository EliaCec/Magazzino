package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.GambaScrivania;

public class RepartoGambaScrivania extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 300;
	
	// costruttore
	public RepartoGambaScrivania() {
		super(CAPACITA, NomiReparti.REPARTO_GAMBA_SCRIVANIA);
	}

	protected Giacenza creaGiacenza() {
		return new GambaScrivania();
	}
	
}
