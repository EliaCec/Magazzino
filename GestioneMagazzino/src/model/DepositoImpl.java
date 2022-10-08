package model;

import java.util.Date;

public class DepositoImpl implements Deposito{
	
	private final Semilavorato semilavorato;       // semilavorato da depositare
	private final Responsabile responsabile;       // responsabile che lo deposita
	private final Date data;                       // momento in cui lo deposita
	
	// costruttore
	public DepositoImpl(Semilavorato s, Responsabile r, Date d) {
		this.semilavorato = s;
		this.responsabile = r;
		this.data 		  = d;
	}

	public Semilavorato getSemilavorato() {
		return semilavorato;
	}

	public Responsabile getResponsabile() {
		return responsabile;
	}

	public Date getData() {
		return data;
	}
	
	
}
