package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.PannelloGrandeArmadio;

public class RepartoPannelloGrandeArmadio extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoPannelloGrandeArmadio() {
		super(CAPACITA, NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		return new PannelloGrandeArmadio();
	}

}
