package model;

import java.util.LinkedList;
import java.util.List;

public class RepartoImpl implements Reparto {
	private static int NUOVO_NUMERO_REPARTO = 0; // codice univoco che verrà assegnato all'eventuale nuovo reparto che (e se) verrà creato
	private final int numeroReparto;			 // codice univoco del reparto
	private final String nome;  				 // nome del reparto
	private List<Giacenza> scorte; 				 // scorte presenti nel reparto
	private final int capacita;					 // capacità del magazzino
	
	// costruttore
	public RepartoImpl(int c, String name) {
		this.numeroReparto 	  = RepartoImpl.NUOVO_NUMERO_REPARTO;
		this.nome 	  		  = "reparto_" + name;
		this.scorte 		  = new LinkedList<>();
		this.capacita 		  = c;
		RepartoImpl.NUOVO_NUMERO_REPARTO++;
	}

	public int getId() {
		return this.numeroReparto;
	}

	public String getNome() {
		return this.nome;
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

	public boolean depositaScorte(Giacenza g) {
		// il deposito avviene solamente se il reparto non è pieno
		if (!this.isPieno()) {
			this.scorte.add(g);
			return true;
		}
		return false;
	}

	public boolean prelevaScorte(int n) {
		// il prelievo avviene solamente se nel reparto sono presenti almeno n scorte
		if (n <= this.scorte.size()) {
			for (int i = 0; i < n; i++)
				this.scorte.remove(0);
			return true;
		}
		return false;
	}
	
}
