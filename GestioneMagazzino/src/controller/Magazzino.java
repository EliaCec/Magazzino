package controller;

import java.util.Date;

import model.Dirigente;
import model.RepartoProdottiFiniti;
import model.classi.exception.RepartoPienoException;
import model.classi.exception.SemilavoratiInsufficientiException;

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
	public RepartoProdottiFiniti getRepPfinit(String pf) {
		for (int i = 0; i < this.dir.getReparti().size(); i++) {
			if(this.dir.getReparti().get(i).getGiacenzaReparto().getNome().equals(pf)) {
				return this.dir.getReparti().get(i);
			}
		}
		return null;
	}
	
	public String costruisciProdotti(RepartoProdottiFiniti rep, int o, int n, Date giorno) {
		try {
			dir.getOperaiAttivi().get(o).costruisciProdottiFiniti(rep, n, giorno);
			return "prodotto costruito con successo";
		}catch(RepartoPienoException e){
			return "hai costruito troppi prodotti finiti, spazio insufficente!!";
		}catch(SemilavoratiInsufficientiException f) {
			return "Semilavorati insufficenti";
		}
	}
}
