package model.classi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import model.*;

public class MagazzinoImpl implements Magazzino {
	private List<Responsabile> responsabili;				// responsabili totali
	private List<Operaio> operai;							// operai totali
	private List<Responsabile> responsabiliAttivi;			// responsabili che stanno lavorando
	private List<Operaio> operaiAttivi;						// operai che stanno lavorando
	private List<RepartoSemilavorati> reparti;				// reparti che compongono il magazzino
	private Date turnoCorrente;								// data e ora che indica l'orario di inizio del turno corrente
	
	// costruttore
	public MagazzinoImpl() {
		this.responsabili 		= new LinkedList<>();
		this.operai 			= new LinkedList<>();
		this.responsabiliAttivi = new LinkedList<>();
		this.operaiAttivi 		= new LinkedList<>();
		this.reparti 			= new LinkedList<>();
		this.turnoCorrente		= new Date();
	}

	public int getNumeroReparti() {
		return this.reparti.size();
	}
	
	public List<Vendita> storicoVenditeGiornaliero(Date giorno) {
		return this.responsabili.stream()
								.map(r -> r.getProdottiVenduti().stream()
																.filter(v -> v.getData().equals(giorno))
																.findAny().get())
								.collect(Collectors.toList());			 
	}
	
	public List<Costruzione> storicoCostruzioniGiornaliero(Date giorno) {
		return this.operai.stream()
						  .map(o -> o.getProdottiCostruiti().stream()
								  							.filter(c -> c.getData().equals(giorno))
						  									.findAny().get())
						  .collect(Collectors.toList());			 
	}

	public List<Prelievo> storicoSemilavoratiUsatiGiornaliero(Date giorno) {
		return this.operai.stream()
				  		  .map(o -> o.getSemilavoratiPrelevati().stream()
						  										.filter(p -> p.getData().equals(giorno))
				  												.findAny().get())
				  		  .collect(Collectors.toList());
	}

	public List<Responsabile> getResponsabiliAttivi() {
		return new LinkedList<>(this.responsabiliAttivi);
	}

	public List<Operaio> getOperaiAttivi() {
		return new LinkedList<>(this.operaiAttivi);
	}

	public boolean cambioTurno(List<Operaio> operai, List<Responsabile> responsabili, Date nuovoTurno) {
		if (nuovoTurno.after(this.turnoCorrente)) {
			this.operaiAttivi = operai;
			this.responsabiliAttivi = responsabili;
			this.turnoCorrente = nuovoTurno;
			return true;
		}
		return false; //!!!!!!!!lancio eccezzione!!!!!!!!!
	}

	public void creaReparto(RepartoProdottiFiniti reparto) {
		this.reparti.add(reparto);
	}
	
	public void assumiDipendente(Dipendente dip) {
		if (dip instanceof Operaio)
			this.operai.add((Operaio)dip);
		else
			this.responsabili.add((Responsabile)dip);
	}
	
}
