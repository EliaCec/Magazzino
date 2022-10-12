package model.reparti;

import model.semilavorati.StaffaMensola;

public class RepartoStaffaMensola extends RepartoImpl{
	
	// costruttore
	public RepartoStaffaMensola() {
		super(200, NomiReparti.REPARTO_STAFFA_MENSOLA);
	}
	
	protected Giacenza creaGiacenza() {
		return new StaffaMensola();
	}
}
