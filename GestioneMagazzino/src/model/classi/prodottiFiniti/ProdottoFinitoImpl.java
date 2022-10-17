package model.classi.prodottiFiniti;
import java.util.HashMap;

import model.ProdottoFinito;
import model.classi.GiacenzaImpl;

public abstract class ProdottoFinitoImpl extends GiacenzaImpl implements ProdottoFinito {

	protected HashMap<String, Integer> componenti;	// mappa che contiene i componenti dei prodotti finiti
	
	// costruttore
	public ProdottoFinitoImpl(String n) {
		super(n);
		this.componenti = new HashMap<>();
	}

	public HashMap<String, Integer> getComponenti() {
		return new HashMap<>(this.componenti);
	}

	protected abstract void inserisciComponenti();

	protected HashMap<String, Integer> getListaComponenti() {
		return this.componenti;
	}
}
