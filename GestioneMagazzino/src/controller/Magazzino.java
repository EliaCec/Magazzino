package controller;

import java.util.Date;

import model.Dirigente;
import model.RepartoProdottiFiniti;
import model.classi.exception.RepartoPienoException;
import model.classi.exception.SemilavoratiInsufficientiException;

public class Magazzino {
	
	// campi interni
	private Dirigente dir;
	private Date dataCorrente; 		// data per controllare che le operazioni avvengano in ordine cronologico
	
	// costruttore
	public Magazzino(Dirigente dir, Date d) {
		this.dir = dir;
		this.dataCorrente = d;
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
	
	// metodo che costruisce i prodotti finiti e stampa il messaggio di conferma/errore
	public String costruisciProdotti(RepartoProdottiFiniti rep, int o, int n, Date giorno) {
		try {
			dir.getOperaiAttivi().get(o).costruisciProdottiFiniti(rep, n, giorno);
			return "prodotto costruito con successo";
		}catch(RepartoPienoException e){
			return e.getMessage();
		}catch(SemilavoratiInsufficientiException f) {
			return f.getMessage();
		}
	}
	
	// metodo che controlla la correttezza delle date, nuovaData deve essere strettamente maggiore della data corrente
	private boolean controllaData(Date nuovaData) {
		return nuovaData.after(this.dataCorrente);
	}
	
}
