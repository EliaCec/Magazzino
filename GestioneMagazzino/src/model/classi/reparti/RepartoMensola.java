package model.classi.reparti;

import model.Giacenza;
import model.classi.prodottiFiniti.Mensola;

public class RepartoMensola extends RepartoProdottoFinitiAbs {
	
	private final static int CAPACITA = 200;
	
	// costruttore
	public RepartoMensola() {
		super(CAPACITA, NomiReparti.REPARTO_MENSOLA);
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
