package model.reparti;

import model.Giacenza;
import model.semilavorati.PannelloGrandeArmadio;

public class RepartoPannelloGrandeArmadio extends RepartoAbs {
	
	// costruttore
	public RepartoPannelloGrandeArmadio() {
		super(200, NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		// TODO Auto-generated method stub
		return new PannelloGrandeArmadio();
	}

}
