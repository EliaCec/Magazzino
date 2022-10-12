package model.reparti;

import model.Giacenza;
import model.semilavorati.BraccioloSedia;

public class RepartoBraccioloSedia extends RepartoAbs {
	
	// costruttore
	public RepartoBraccioloSedia() {
		super(400, NomiReparti.REPARTO_BRACCIOLO_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new BraccioloSedia();
	}
	
}
