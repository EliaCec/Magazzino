package model.reparti;

import model.Giacenza;
import model.prodottiFiniti.Armadio;

public class RepartoArmadio extends RepartoAbs {
	
	// costruttore
	public RepartoArmadio() {
		super(200, NomiReparti.REPARTO_ARMADIO);
	}

	protected Giacenza creaGiacenza() {
		// TODO Auto-generated method stub
		return new Armadio();
	}

}
