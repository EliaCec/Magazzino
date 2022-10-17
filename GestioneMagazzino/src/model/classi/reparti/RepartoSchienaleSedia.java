package model.classi.reparti;

import model.Giacenza;
import model.classi.semilavorati.SchienaleSedia;

public class RepartoSchienaleSedia extends RepartoSemilavoratiAbs {
	
	// costruttore
	public RepartoSchienaleSedia() {
		super(200, NomiReparti.REPARTO_SCHIENALE_SEDIA);
	}
	
	protected Giacenza creaGiacenza() {
		return new SchienaleSedia();
	}
}
