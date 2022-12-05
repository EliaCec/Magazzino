package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.SchienaleSedia;

public class RepartoSchienaleSedia extends RepartoSemilavoratiAbs {
	
	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoSchienaleSedia() {
		super(CAPACITA, NomiReparti.REPARTO_SCHIENALE_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SchienaleSedia();
	}
}
