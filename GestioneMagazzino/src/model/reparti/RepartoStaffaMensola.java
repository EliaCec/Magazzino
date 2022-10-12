package model.reparti;

import model.semilavorati.StaffaMensola;
import model.Giacenza;

public class RepartoStaffaMensola extends RepartoAbs {
	
	// costruttore
	public RepartoStaffaMensola() {
		super(200, NomiReparti.REPARTO_STAFFA_MENSOLA);
	}
	
	protected Giacenza creaGiacenza() {
		return new StaffaMensola();
	}
}
