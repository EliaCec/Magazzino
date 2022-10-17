package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.StaffaMensola;

public class RepartoStaffaMensola extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoStaffaMensola() {
		super(200, NomiReparti.REPARTO_STAFFA_MENSOLA);
	}
	
	protected Giacenza creaGiacenza() {
		return new StaffaMensola();
	}
}
