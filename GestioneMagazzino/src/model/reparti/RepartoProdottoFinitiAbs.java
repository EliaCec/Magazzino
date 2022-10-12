package model.reparti;

import java.util.LinkedList;
import java.util.List;
import model.RepartoProdottiFiniti;
import model.RepartoSemilavorati;

public abstract class RepartoProdottoFinitiAbs extends RepartoSemilavoratiAbs implements RepartoProdottiFiniti {
	
	private List<RepartoSemilavorati> listaRepartiSemilavorati;		//lista di reparti dei semilavorati che compongono il prodotto finito

	public RepartoProdottoFinitiAbs(int c, NomiReparti name) {
		super(c, name);
	}
	
	public List<RepartoSemilavorati> getListaRepartiSemilavorati() {
		return new LinkedList<>(this.listaRepartiSemilavorati);
	}
	
	protected abstract void aggiungiInListaRepartiSemilavorati();
	
	protected List<RepartoSemilavorati> getListaSemi() {
		return this.listaRepartiSemilavorati;
	}
	
}
