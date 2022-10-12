package model.reparti;

import model.Giacenza;
import model.semilavorati.PannelloPiccoloArmadio;

public class RepartoPannelloPiccoloArmadio extends RepartoSemilavoratiAbs{
	
	// costruttore
	public RepartoPannelloPiccoloArmadio() {
		super(300, NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		// TODO Auto-generated method stub
		return new PannelloPiccoloArmadio();
	}
	
}
