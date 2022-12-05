package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.PannelloPiccoloArmadio;

public class RepartoPannelloPiccoloArmadio extends RepartoSemilavoratiAbs{
	
	private final static int CAPACITA = 300;
	
	// costruttore
	public RepartoPannelloPiccoloArmadio() {
		super(CAPACITA, NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		return new PannelloPiccoloArmadio();
	}
}
