package model.classi;

import java.util.Date;

import model.ProdottoFinito;
import model.Responsabile;
import model.Vendita;

public class VenditaImpl implements Vendita{
	
	private final ProdottoFinito prodottoFinito;      // prodotto finito da vendere 
	private final Responsabile responsabile;          // responsabile che lo vende
	private final Date data;                          // momento in cui lo vende
	 
	// costruttore
	public VenditaImpl(ProdottoFinito pf, Responsabile r, Date d) {
		this.prodottoFinito = pf;
		this.responsabile   = r;
		this.data 			= d;
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
