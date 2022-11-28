package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.PannelloPiccoloArmadio;

public class RepartoPannelloPiccoloArmadio extends RepartoSemilavoratiAbs{
	
	// costruttore
	public RepartoPannelloPiccoloArmadio() {
		super(300, NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		return new PannelloPiccoloArmadio();
	}
}
