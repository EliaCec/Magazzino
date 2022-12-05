package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.PianaleScrivania;

public class RepartoPianaleScrivania extends RepartoSemilavoratiAbs{
	
	private final static int CAPACITA = 375;
	
	// costruttore
	public RepartoPianaleScrivania() {
		super(CAPACITA, NomiReparti.REPARTO_PIANALE_SCRIVANIA);
	}

	@Override
	protected Giacenza creaGiacenza() {
		return new PianaleScrivania();
	}
	
}
