package model.reparti;

import java.util.LinkedList;
import java.util.List;

import model.Giacenza;
import model.RepartoSemilavorati;

public abstract class RepartoAbs implements RepartoSemilavorati {
	private static int NUOVO_NUMERO_REPARTO = 0; // codice univoco che verrà assegnato all'eventuale nuovo reparto che (e se) verrà creato
	private final int numeroReparto;			 // codice univoco del reparto
	private final NomiReparti nome;  			 // nome del reparto
	private List<Giacenza> scorte; 				 // scorte presenti nel reparto
	private final int capacita;					 // capacità del magazzino
	
	// costruttore
	public RepartoAbs(int c, NomiReparti name) {
		this.numeroReparto 	  = RepartoAbs.NUOVO_NUMERO_REPARTO;
		this.nome 	  		  = name;
		this.scorte 		  = new LinkedList<>();
		this.capacita 		  = c;
		RepartoAbs.NUOVO_NUMERO_REPARTO++;
	}

	public int getId() {
		return this.numeroReparto;
	}

	public String getNome() {
		return this.nome.getNome();
	}

	public int getQuantita() {
		return this.scorte.size();
	}

	public int getCapacita() {
		return this.capacita;
	}

	public boolean isPresente() {
		return this.scorte.size() > 0;
	}

	public boolean isPieno() {
		return this.scorte.size() == this.capacita;
	}

	public Giacenza depositaScorte() {
		// il deposito avviene solamente se il reparto non è pieno
		Giacenza daDepositare = creaGiacenza();
		if (!this.isPieno()) {
			this.scorte.add(daDepositare);
			return daDepositare;
		}
		return null;  //!!!!!!!!aggiungere lancio eccezzione!!!!!!!!
	}

	public Giacenza prelevaScorte() {
		// il prelievo avviene solamente se nel reparto è presente la scorta
		if (this.isPresente())
			return this.scorte.remove(0);
		return null; //!!!!!!!!aggiungere lancio eccezzione!!!!!!!!
	}
	
	public Giacenza getGiacenzaReparto() {
		return this.scorte.get(0);
	}
	
	protected abstract Giacenza creaGiacenza();
	
}

