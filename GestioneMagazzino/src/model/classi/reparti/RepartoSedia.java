package model.classi.reparti;

import model.Giacenza;
import model.classi.prodottiFiniti.Sedia;


public class RepartoSedia extends RepartoProdottoFinitiAbs {
	
	// costruttore
	public RepartoSedia() {
		super(200, NomiReparti.REPARTO_SEDIA);
		this.aggiungiInListaRepartiSemilavorati();
	}

	protected Giacenza creaGiacenza() {
		return new Sedia();
	}

	protected void aggiungiInListaRepartiSemilavorati() {
		this.getListaSemi().add(new RepartoSedutaSedia());
		this.getListaSemi().add(new RepartoSchienaleSedia());
		this.getListaSemi().add(new RepartoBraccioloSedia());
		this.getListaSemi().add(new RepartoGambaSedia());
	}
}
