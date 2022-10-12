package model.reparti;

import model.prodottiFiniti.Scrivania;
import model.Giacenza;

public class RepartoScrivania extends RepartoAbs {

	// costruttore
	public RepartoScrivania() {
		super(200, NomiReparti.REPARTO_SCRIVANIA);
	}
		
	protected Giacenza creaGiacenza() {
		return new Scrivania();
	}
}
