package model.prodottiFiniti;
import java.util.HashMap;

import model.GiacenzaImpl;
import model.ProdottoFinito;

public abstract class ProdottoFinitoImpl extends GiacenzaImpl implements ProdottoFinito {

	protected HashMap<String, Integer> componenti;	// mappa che contiene i componenti dei prodotti finiti
	
	// costruttore
	public ProdottoFinitoImpl(String n) {
		super(n, "prodotto_finito");
		this.componenti = new HashMap<>();
	}

	public HashMap<String, Integer> getComponenti() {
		return this.componenti;
	}

	public abstract void inserisciComponenti();

}
