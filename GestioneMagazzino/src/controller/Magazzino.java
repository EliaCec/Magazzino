package controller;

import model.Dirigente;

public class Magazzino {
	
	// campo interno
	private Dirigente dir;
	
	// costruttore
	public Magazzino(Dirigente dir) {
		this.dir = dir;
	}
	
	// metodo getter
	public Dirigente getDirigente() {
		return this.dir;
	}
	
}
