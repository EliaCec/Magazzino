package model.reparti;

import model.Giacenza;
import model.prodottiFiniti.Armadio;

public class RepartoArmadio extends RepartoProdottoFinitiAbs {
	
	// costruttore
	public RepartoArmadio() {
		super(200, NomiReparti.REPARTO_ARMADIO);
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
