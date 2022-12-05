package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.BraccioloSedia;

public class RepartoBraccioloSedia extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 400;
	
	// costruttore
	public RepartoBraccioloSedia() {
		super(CAPACITA, NomiReparti.REPARTO_BRACCIOLO_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new BraccioloSedia();
	}
	
}
