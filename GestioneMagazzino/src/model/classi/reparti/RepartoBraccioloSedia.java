package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.BraccioloSedia;

public class RepartoBraccioloSedia extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoBraccioloSedia() {
		super(400, NomiReparti.REPARTO_BRACCIOLO_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new BraccioloSedia();
	}
	
}
