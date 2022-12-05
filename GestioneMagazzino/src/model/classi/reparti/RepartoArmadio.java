package model.classi.reparti;

import model.Giacenza;
import model.classi.prodottiFiniti.Armadio;

public class RepartoArmadio extends RepartoProdottoFinitiAbs {
	
	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoArmadio() {
		super(CAPACITA, NomiReparti.REPARTO_ARMADIO);
		this.aggiungiInListaRepartiSemilavorati();
	}

	protected Giacenza creaGiacenza() {
		return new Armadio();
	}

	protected void aggiungiInListaRepartiSemilavorati() {
		this.getListaSemi().add(new RepartoAntaArmadio());
		this.getListaSemi().add(new RepartoPannelloGrandeArmadio());
		this.getListaSemi().add(new RepartoPannelloPiccoloArmadio());
	}

}
