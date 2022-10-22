package model.classi.reparti;

import java.util.LinkedList;
import java.util.List;

import model.Giacenza;
import model.RepartoSemilavorati;

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
		Giacenza daDepositare = creaGiacenza();
		this.scorte.add(daDepositare);
		return daDepositare;
	}

	public Giacenza prelevaScorte() {
		return this.scorte.remove(0);
	}
	
	public Giacenza getGiacenzaReparto() {
		Giacenza g = creaGiacenza();
		g.setId(g.getId() - 1);
		return g;
	}
	
	protected abstract Giacenza creaGiacenza();
	
}

