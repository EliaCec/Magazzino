package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.PianaleScrivania;

public class RepartoPianaleScrivania extends RepartoSemilavoratiAbs{
	
	// costruttore
	public RepartoPianaleScrivania() {
		super(375, NomiReparti.REPARTO_PIANALE_SCRIVANIA);
	}

	@Override
	protected Giacenza creaGiacenza() {
		return new PianaleScrivania();
	}
	
}
