package model.classi.reparti;


import java.util.LinkedList;
import java.util.List;

import model.Giacenza;
import model.RepartoSemilavorati;
import model.classi.GeneratoreID;

public abstract class RepartoSemilavoratiAbs implements RepartoSemilavorati {
	private final NomiReparti nome;  			 // nome del reparto
	private final List<Giacenza> scorte; 		 // scorte presenti nel reparto
	private final int capacita;					 // capacità del magazzino
	
	// costruttore
	public RepartoSemilavoratiAbs(int c, NomiReparti name) {
		this.nome 	  		  = name;
		this.scorte 		  = new LinkedList<>();
		this.capacita 		  = c;
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
		GeneratoreID.decrementaIdGiacenza();
		return g;
	}
	
	protected abstract Giacenza creaGiacenza();
	
}

