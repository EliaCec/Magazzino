package model.reparti;

import model.semilavorati.SedutaSedia;
import model.semilavorati.StaffaMensola;

public class RepartoSedutaSedia extends RepartoImpl{
	
	// costruttore
	public RepartoSedutaSedia() {
		super(200, NomiReparti.REPARTO_SEDUTA_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SedutaSedia();
	}
}
