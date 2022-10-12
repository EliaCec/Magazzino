package model.reparti;

import model.Giacenza;
import model.prodottiFiniti.Mensola;

public class RepartoMensola extends RepartoAbs {
	
	// costruttore
	public RepartoMensola() {
		super(200, NomiReparti.REPARTO_MENSOLA);
	}

	protected Giacenza creaGiacenza() {
		return new Mensola();
	}

}
