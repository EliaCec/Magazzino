package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.RipianoMensola;

public class RepartoRipianoMensola extends RepartoSemilavoratiAbs {

	// costruttore
	public RepartoRipianoMensola() {
		super(350, NomiReparti.REPARTO_RIPIANO_MENSOLA);
	}
		
	protected Giacenza creaGiacenza() {
		return new RipianoMensola();
	}
}
