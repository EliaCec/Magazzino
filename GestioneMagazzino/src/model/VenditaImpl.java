package model;

import java.util.Date;

public class VenditaImpl implements Vendita{
	
	private final ProdottoFinito prodottoFinito;
	private final Responsabile responsabile;
	private final Date data;
	
	// costruttore
	public VenditaImpl(ProdottoFinito pf, Responsabile r, Date d) {
		this.prodottoFinito = pf;
		this.responsabile = r;
		this.data = d;
	}

	public ProdottoFinito getProdottoFinito() {
		return this.prodottoFinito;
	}

	public Responsabile getResponsabile() {
		return this.responsabile;
	}

	public Date getData() {
		return this.data;
	}
	
	

}
