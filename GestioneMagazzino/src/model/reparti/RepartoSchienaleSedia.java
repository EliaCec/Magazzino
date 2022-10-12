package model.reparti;

import model.semilavorati.SchienaleSedia;
import model.semilavorati.StaffaMensola;

public class RepartoSchienaleSedia extends RepartoImpl {
	
	// costruttore
	public RepartoSchienaleSedia() {
		super(200, NomiReparti.REPARTO_SCHIENALE_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SchienaleSedia();
	}
}
