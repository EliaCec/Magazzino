package controller;

import model.Dirigente;
import model.RepartoProdottiFiniti;

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
	
	// metodo per ritornare il reparto con un prodotto finito
	public RepartoProdottiFiniti repPfinit(String pf) {
		for (int i = 0; i < this.dir.getReparti().size(); i++) {
			if(this.dir.getReparti().get(i).getGiacenzaReparto().getNome().equals(pf)) {
				return this.dir.getReparti().get(i);
			}
		}
		return null;
	}
}
