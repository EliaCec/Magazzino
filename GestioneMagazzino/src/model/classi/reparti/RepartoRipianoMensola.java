package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.RipianoMensola;

public class RepartoRipianoMensola extends RepartoSemilavoratiAbs {

	private final static int CAPACITA = 350;
	
	// costruttore
	public RepartoRipianoMensola() {
		super(CAPACITA, NomiReparti.REPARTO_RIPIANO_MENSOLA);
	}
		
	protected Giacenza creaGiacenza() {
		return new RipianoMensola();
	}
}
