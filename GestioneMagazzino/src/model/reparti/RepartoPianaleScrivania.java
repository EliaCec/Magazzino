package model.reparti;

import model.Giacenza;
import model.semilavorati.PianaleScrivania;

public class RepartoPianaleScrivania extends RepartoAbs{
	
	// costruttore
	public RepartoPianaleScrivania() {
		super(375, NomiReparti.REPARTO_PIANALE_SCRIVANIA);
	}

	@Override
	protected Giacenza creaGiacenza() {
		// TODO Auto-generated method stub
		return new PianaleScrivania();
	}
	
}
