package model.classi.reparti;

import model.Giacenza;
import model.classi.prodottiFiniti.Scrivania;

public class RepartoScrivania extends RepartoProdottoFinitiAbs {

	// costruttore
	public RepartoScrivania() {
		super(200, NomiReparti.REPARTO_SCRIVANIA);
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
