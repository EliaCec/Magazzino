package model.reparti;

import model.Giacenza;
import model.prodottiFiniti.Mensola;

public class RepartoMensola extends RepartoProdottoFinitiAbs {
	
	// costruttore
	public RepartoMensola() {
		super(200, NomiReparti.REPARTO_MENSOLA);
		this.aggiungiInListaRepartiSemilavorati();
	}

	protected Giacenza creaGiacenza() {
		return new Mensola();
	}

	protected void aggiungiInListaRepartiSemilavorati() {
		this.getListaSemi().add(new RepartoRipianoMensola());
		this.getListaSemi().add(new RepartoStaffaMensola());
	}
	
	

}
