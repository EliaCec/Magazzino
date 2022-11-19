package model.classi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import model.*;
import model.classi.exception.TurnoInvalidoException;

public class DirigenteImpl extends DipendenteImpl implements Dirigente {
	private List<Responsabile> responsabili;				// responsabili totali
	private List<Operaio> operai;							// operai totali
	private List<Responsabile> responsabiliAttivi;			// responsabili che stanno lavorando
	private List<Operaio> operaiAttivi;						// operai che stanno lavorando
	private List<RepartoProdottiFiniti> reparti;			// reparti che compongono il magazzino
	private Date turnoCorrente;								// data e ora che indica l'orario di inizio del turno corrente
	
	// costruttore
	@SuppressWarnings("deprecation")
	public DirigenteImpl() {
		super("DIRIGENTE");
		this.responsabili 		= new LinkedList<>();
		this.operai 			= new LinkedList<>();
		this.responsabiliAttivi = new LinkedList<>();
		this.operaiAttivi 		= new LinkedList<>();
		this.reparti 			= new LinkedList<>();
		this.turnoCorrente		= new Date(2022, 1, 01, 0, 0);
	}

	public int getNumeroReparti() {
		return this.reparti.size();
	}
	
	@SuppressWarnings("deprecation")
	public List<Vendita> storicoVenditeGiornaliero(Date giorno) {
		return this.responsabili.stream()
								.map(r -> r.getProdottiVenduti().stream()
																.filter(v -> v.getData().getDay() == giorno.getDay() &&
																		 	 v.getData().getMonth() == giorno.getMonth() && 
																		 	 v.getData().getYear() == giorno.getYear())
																.collect(Collectors.toList()))
								.findAny().get();			 
	}
	
	@SuppressWarnings("deprecation")
	public List<Costruzione> storicoCostruzioniGiornaliero(Date giorno) {
		return this.operai.stream()
						  .map(o -> o.getProdottiCostruiti().stream()
								  							.filter(c -> c.getData().getDay() == giorno.getDay() &&
								  									     c.getData().getMonth() == giorno.getMonth() && 
								  									     c.getData().getYear() == giorno.getYear())
								  							.collect(Collectors.toList()))
						  .findAny().get();			 
	/*	List<Costruzione> ciao = new LinkedList<>();
		for(int i = 0; i< operai.size() ;i++) {
			for(int j = 0; j< operai.get(i).getProdottiCostruiti().size() ;j++) {
				if(operai.get(i).getProdottiCostruiti().get(j).getData().getDay() == giorno.getDay() &&
					operai.get(i).getProdottiCostruiti().get(j).getData().getMonth() == giorno.getMonth() &&
					operai.get(i).getProdottiCostruiti().get(j).getData().getYear() == giorno.getYear()) {
					ciao.add(operai.get(i).getProdottiCostruiti().get(j));
				}				
			}
		}
		return ciao;*/
	}

	@SuppressWarnings("deprecation")
	public List<Prelievo> storicoSemilavoratiUsatiGiornaliero(Date giorno) {
		return this.operai.stream()
				  		  .map(o -> o.getSemilavoratiPrelevati().stream()
				  				  								.filter(p -> p.getData().getDay() == giorno.getDay() &&
				  				  											 p.getData().getMonth() == giorno.getMonth() && 
				  				  											 p.getData().getYear() == giorno.getYear())
				  				  								.collect(Collectors.toList()))
				  		  .findAny().get();			 
	}

	public List<Responsabile> getResponsabiliAttivi() {
		return new LinkedList<>(this.responsabiliAttivi);
	}

	public List<Operaio> getOperaiAttivi() {
		return new LinkedList<>(this.operaiAttivi);
	}

	public void cambioTurno(List<Operaio> operai, List<Responsabile> responsabili, Date nuovoTurno) {
		if (nuovoTurno.after(this.turnoCorrente)) {
			this.operaiAttivi = operai;
			this.responsabiliAttivi = responsabili;
			this.turnoCorrente = nuovoTurno;
		} else {
			throw new TurnoInvalidoException("L'ultimo turno è iniziato il: " + this.turnoCorrente + ", inserire un turno con data/ora successivi a questo");
		}
	}

	public void creaReparto(RepartoProdottiFiniti reparto) {
		this.reparti.add(reparto);
	}
	
	public void assumiDipendente(Dipendente dip) {
		if (dip instanceof Operaio)
			this.operai.add((Operaio)dip);
		else if (dip instanceof Responsabile)
			this.responsabili.add((Responsabile)dip);
	}
	
	public List<Operaio> getOperaiAssunti(){
		return new LinkedList<>(this.operai);
	}
	
	public List<Responsabile> getResponsabiliAssunti(){
		return new LinkedList<>(this.responsabili);
	}
	
	public Dipendente cercaDipendentePerNome(String nome, List<? extends Dipendente> lista) {
		return lista.stream()
					.filter(d -> d.getNomeCognome().equals(nome))
					.findAny()
					.get();
	}
	
	public RepartoSemilavorati cercaRepartoPerNome(String nome, List<? extends RepartoSemilavorati> lista) {
		return lista.stream()
					.filter(r -> r.getNome().equals(nome))
					.findAny()
					.get();
	}

	public List<RepartoProdottiFiniti> getReparti() {
		return new LinkedList<>(this.reparti);
	}
	
}
