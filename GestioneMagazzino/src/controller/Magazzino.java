package controller;

import java.util.Date;
import java.util.List;

import model.Dirigente;
import model.RepartoProdottiFiniti;
import model.RepartoSemilavorati;
import model.classi.exception.DataErrataException;
import model.classi.exception.ProdottiInsufficientiException;
import model.classi.exception.RepartoPienoException;
import model.classi.exception.SemilavoratiInsufficientiException;
import model.Operaio;
import model.Responsabile;

public class Magazzino {
	
	// campi interni
	private Dirigente dir;
	private Date dataCorrente; 		// data per controllare che le operazioni avvengano in ordine cronologico
	
	// costruttore
	public Magazzino(Dirigente dir) {
		this.dir = dir;
		this.dataCorrente = new Date();
	}
	
	// metodo getter
	public Dirigente getDirigente() {
		return this.dir;
	}
	
	// metodo per ritornare il reparto con un semilavorato
	public RepartoSemilavorati getRepSemi(String pf) {
		for (int i = 0; i < this.dir.getReparti().size(); i++) {
			for(int j = 0; j < this.dir.getReparti().get(i).getListaRepartiSemilavorati().size(); j++) {
				if(this.dir.getReparti().get(i).getListaRepartiSemilavorati().get(j).getGiacenzaReparto().getNome().equals(pf)) {
					return this.dir.getReparti().get(i).getListaRepartiSemilavorati().get(j);
				}
			}
		}
		return null;
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
	
	// metodo che costruisce i prodotti finiti e restituisce il messaggio di conferma/errore
	public String costruisciProdotti(RepartoProdottiFiniti rep, int o, int n, Date giorno) {
		try {
			this.controllaData(giorno);
			this.dataCorrente = giorno;
			this.dir.getOperaiAttivi().get(o).costruisciProdottiFiniti(rep, n, giorno);
			return "prodotto costruito con successo";
		} catch(RepartoPienoException e){
			return e.getMessage();
		} catch(SemilavoratiInsufficientiException f) {
			return f.getMessage();
		} catch(DataErrataException d) {
			return d.getMessage();
		}
	}
	
	// metodo che vende i prodotti finiti e restituisce il messaggio di conferma/errore
	public String vendiProdotti(RepartoProdottiFiniti rep, int r, int n, Date giorno) {
		try {
			this.controllaData(giorno);
			this.dataCorrente = giorno;
			this.dir.getResponsabiliAttivi().get(r).vendiProdottiFiniti(rep, n, giorno);
			return "prodotto venduto con successo";
		} catch(ProdottiInsufficientiException p) {
			return p.getMessage();
		} catch(DataErrataException d) {
			return d.getMessage();
		}
	}
	
	// metodo che deposita i semilavorati e restituisce il messaggio di conferma/errore
	public String depositaSemilavorati(RepartoSemilavorati rep, int r, int n, Date giorno) {
		try {
			this.controllaData(giorno);
			this.dataCorrente = giorno;
			this.dir.getResponsabiliAttivi().get(r).depositaSemilavorati(rep, n, giorno);
			return "semilavorati depositati con successo";
		} catch(RepartoPienoException e) {
			return e.getMessage();
		} catch(DataErrataException d) {
			return d.getMessage();
		}
	}
	
	// metodo che effettua il cambio di turno e restituisce il messaggio di conferma/errore
	public String cambioTurno(List<Operaio> nuoviOperai, List<Responsabile> nuoviResponsabili, Date data) {
		try {
			this.controllaData(data);
			this.dir.cambioTurno(nuoviOperai, nuoviResponsabili, data);
			return "cambio turno avvenuto con successo";
		} catch(DataErrataException d) {
			return d.getMessage();
		}
	}
	
	// metodo che controlla la correttezza delle date, nuovaData deve essere strettamente maggiore della data corrente
	private void controllaData(Date nuovaData) {
		if (!nuovaData.after(this.dataCorrente)) {
			throw new DataErrataException("L'ultima operazione effettuata nel magazzino è stata il: " + this.dataCorrente + ", inserisci un'operazione con data/ora successive a questa");
		}
	}
	
}
