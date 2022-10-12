package model.reparti;

import model.semilavorati.RipianoMensola;
import model.semilavorati.StaffaMensola;

public class RepartoRipianoMensola extends RepartoImpl {
	
	// costruttore
	public RepartoRipianoMensola() {
		super(350, NomiReparti.REPARTO_RIPIANO_MENSOLA);
	}
		
	protected Giacenza creaGiacenza() {
		return new RipianoMensola();
	}
}
