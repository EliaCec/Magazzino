package model.classi.reparti;

import model.Giacenza;
import model.classi.prodottiFiniti.Sedia;


public class RepartoSedia extends RepartoProdottoFinitiAbs {
	
	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoSedia() {
		super(CAPACITA, NomiReparti.REPARTO_SEDIA);
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
