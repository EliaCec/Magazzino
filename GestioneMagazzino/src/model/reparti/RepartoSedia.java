package model.reparti;

import model.prodottiFiniti.Sedia;
import model.semilavorati.StaffaMensola;

public class RepartoSedia extends RepartoImpl {
	
	// costruttore
	public RepartoSedia() {
		super(200, NomiReparti.REPARTO_SEDIA);
	}

	protected Giacenza creaGiacenza() {
		return new Sedia();
	}
}
