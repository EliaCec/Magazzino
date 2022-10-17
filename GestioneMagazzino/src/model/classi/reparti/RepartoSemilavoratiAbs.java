package model.classi.reparti;

import java.util.LinkedList;
import java.util.List;

import model.Giacenza;
import model.RepartoSemilavorati;
import model.classi.exception.IllegalException;

public abstract class RepartoSemilavoratiAbs implements RepartoSemilavorati {
	private static int NUOVO_NUMERO_REPARTO = 0; // codice univoco che verrà assegnato all'eventuale nuovo reparto che (e se) verrà creato
	private final int numeroReparto;			 // codice univoco del reparto
	private final NomiReparti nome;  			 // nome del reparto
	private List<Giacenza> scorte; 				 // scorte presenti nel reparto
	private final int capacita;					 // capacità del magazzino
	
	// costruttore
	public RepartoSemilavoratiAbs(int c, NomiReparti name) {
		this.numeroReparto 	  = RepartoSemilavoratiAbs.NUOVO_NUMERO_REPARTO;
		this.nome 	  		  = name;
		this.scorte 		  = new LinkedList<>();
		this.capacita 		  = c;
		RepartoSemilavoratiAbs.NUOVO_NUMERO_REPARTO++;
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

	public Giacenza depositaScorte(){
		// il deposito avviene solamente se il reparto non è pieno
		Giacenza daDepositare = creaGiacenza();
		if (!this.isPieno()) {
			this.scorte.add(daDepositare);
			return daDepositare;
		}else
			throw new IllegalException("Magazzino pieno");  //!!!!!!!!aggiungere lancio eccezzione!!!!!!!!
	}

	public Giacenza prelevaScorte() {
		// il prelievo avviene solamente se nel reparto è presente la scorta
		if (this.isPresente())
			return this.scorte.remove(0);
		else 
			throw new IllegalException("Nel reparto non è presente la scorta"); //!!!!!!!!aggiungere lancio eccezzione!!!!!!!!
	}
	
	public Giacenza getGiacenzaReparto() {
		return this.scorte.get(0);
	}
	
	protected abstract Giacenza creaGiacenza();
	
}

