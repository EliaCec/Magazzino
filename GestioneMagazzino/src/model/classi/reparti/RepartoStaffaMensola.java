package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.StaffaMensola;

public class RepartoStaffaMensola extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoStaffaMensola() {
		super(CAPACITA, NomiReparti.REPARTO_STAFFA_MENSOLA);
	}
	
	protected Giacenza creaGiacenza() {
		return new StaffaMensola();
	}
}
