package model.classi;

import java.util.Date;

import model.Operaio;
import model.Prelievo;
import model.Semilavorato;

public class PrelievoImpl implements Prelievo {
	
	private final Operaio operaio;				// Operaio che ha prelevato il prodotto 
	private final Semilavorato semi;			// semilavorato prelevato
	private final Date data;					// Quando è stato prelevato

	// costruttore
	public PrelievoImpl(Operaio o, Semilavorato s, Date d){
		this.operaio 	= o;
		this.semi	 	= s;
		this.data 		= d;
	}
	
	public Operaio getOperaio() {
		return this.operaio;
	}

	@Override
	public Semilavorato getSemilavorato() {
		return this.semi;
	}

	@Override
	public Date getData() {
		return this.data;
	}

}
