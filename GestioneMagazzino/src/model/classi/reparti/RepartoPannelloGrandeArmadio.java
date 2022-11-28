package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.PannelloGrandeArmadio;

public class RepartoPannelloGrandeArmadio extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoPannelloGrandeArmadio() {
		super(200, NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		return new PannelloGrandeArmadio();
	}

}
