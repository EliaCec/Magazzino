package model;

import java.util.Date;

public class DepositaImpl implements Deposita{
	
	private final Semilavorato semilavorato;
	private final Responsabile responsabile;
	private final Date data;
	
	// costruttore
	public DepositaImpl(Semilavorato s, Responsabile r, Date d) {
		this.semilavorato = s;
		this.responsabile = r;
		this.data = d;
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
