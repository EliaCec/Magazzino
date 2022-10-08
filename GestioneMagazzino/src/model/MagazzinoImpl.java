package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MagazzinoImpl implements Magazzino {
	private static final int DURATA_TURNO = 5;				// durata in ore di ogni turno
	private static final int INIZIO_GIORNATA = 8;			// ora di apertuare del magazzino (e quindi del primo turno della giornata)
	private static final int FINE_GIORNATA = 18;			// ora di chiusura del magazzino (e quindi fine lavoro per tutti i lavoratori)
	
	private List<Responsabile> responsabili;				// responsabili totali
	private List<Operaio> operai;							// operai totali
	private List<Responsabile> responsabiliAttivi;			// responsabili che stanno lavorando
	private List<Operaio> operaiAttivi;						// operai che stanno lavorando
	private List<Reparto> reparti;							// reparti che compongono il magazzino
	private Date turnoCorrente;								// data e ora che indica l'orario di inizio del turno corrente
	
	// costruttore
	public MagazzinoImpl(LinkedList<Responsabile> responsabiliAssunti, LinkedList<Operaio> operaiAssunti) {
		this.responsabili 		= responsabiliAssunti;
		this.operai 			= operaiAssunti;
		this.responsabiliAttivi = new LinkedList<>();
		this.operaiAttivi 		= new LinkedList<>();
		this.reparti 			= new LinkedList();
		this.turnoCorrente		= new Date();
	}

	public int getNumeroReparti() {
		return this.reparti.size();
	}
	
	public List<Vendita> storicoVenditeGiornaliero(Date giorno) {
		
	}
	
	public List<ProdottoFinito> storicoCostruzioniGiornaliero(Date giorno) {
		
	}

	public List<Semilavorato> storicoSemilavoratiUsatiGiornaliero(Date giorno) {
		
	}

	public List<Responsabile> getResponsabiliAttivi() {
		
	}

	public List<Operaio> getOperaiAttivi() {
		
	}

	public boolean cambioTurno(LinkedList<Operaio> operai, LinkedList<Responsabile> responsabili, Date nuovoTurno) {
		if (giorno.getYear() >= this.turnoCorrente.getYear() &&
			giorno.getMonth() >= this.turnoCorrente.getMonth() &&
			giorno.getDay() >= this.turnoCorrente.getDay() &&
			giorno.getHours() > this.turnoCorrente.getHours()) {
			this.operaiAttivi = operai;
			this.responsabiliAttivi = responsabili;
			this.turnoCorrente = nuovoTurno;
			return true;
		} else {
			return false;
		}
	}
	
}
