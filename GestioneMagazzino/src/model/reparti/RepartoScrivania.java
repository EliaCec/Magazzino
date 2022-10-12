package model.reparti;

import model.prodottiFiniti.Scrivania;
import model.semilavorati.StaffaMensola;

public class RepartoScrivania extends RepartoImpl {
	
	// costruttore
	public RepartoScrivania() {
		super(200, NomiReparti.REPARTO_SCRIVANIA);
	}
		
	protected Giacenza creaGiacenza() {
		return new Scrivania();
	}
}
