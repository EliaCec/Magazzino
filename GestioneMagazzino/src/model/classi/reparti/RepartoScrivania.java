package model.classi.reparti;

import model.Giacenza;
import model.classi.prodottiFiniti.Scrivania;

public class RepartoScrivania extends RepartoProdottoFinitiAbs {

	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoScrivania() {
		super(CAPACITA, NomiReparti.REPARTO_SCRIVANIA);
		this.aggiungiInListaRepartiSemilavorati();
	}
		
	protected Giacenza creaGiacenza() {
		return new Scrivania();
	}

	protected void aggiungiInListaRepartiSemilavorati() {
		this.getListaSemi().add(new RepartoPianaleScrivania());
		this.getListaSemi().add(new RepartoGambaScrivania());
	}
}
