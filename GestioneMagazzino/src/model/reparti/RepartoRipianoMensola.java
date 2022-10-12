package model.reparti;

import model.semilavorati.RipianoMensola;
import model.Giacenza;

public class RepartoRipianoMensola extends RepartoSemilavoratiAbs {

	// costruttore
	public RepartoRipianoMensola() {
		super(350, NomiReparti.REPARTO_RIPIANO_MENSOLA);
	}
		
	protected Giacenza creaGiacenza() {
		return new RipianoMensola();
	}
}
