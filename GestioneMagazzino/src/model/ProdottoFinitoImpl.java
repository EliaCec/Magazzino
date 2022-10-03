package model;

import java.util.HashMap;

public class ProdottoFinitoImpl extends GiacenzaImpl implements ProdottoFinito {

	private HashMap<Semilavorato, Integer> componenti;		// 
	
	// costruttore
	public ProdottoFinitoImpl(String n, int i) {
		super(n, i, "prodotto_finito");
		this.componenti = new HashMap<>();
	}

	public HashMap<Semilavorato, Integer> getComponenti() {
		// TODO Auto-generated method stub
		return null;
	}

}
