package model;

import java.util.Date;

public class CostruzioneImpl implements Costruzione  {
	
	private final Operaio operaio;				// Operaio che ha costruito il prodotto 
	private final ProdottoFinito prodotto;		// Prodotto costruito
	private final Date data;					// Quando è stato costruito

	// costruttore
	public CostruzioneImpl(Operaio o, ProdottoFinito p, Date d){
		this.operaio 	= o;
		this.prodotto 	= p;
		this.data 		= d;
	}
	
	public Operaio getOperaio() {
		return this.operaio;
	}

	@Override
	public ProdottoFinito getProdotto() {
		return this.prodotto;
	}

	@Override
	public Date getData() {
		return this.data;
	}

}
