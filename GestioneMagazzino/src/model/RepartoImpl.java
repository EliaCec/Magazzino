package model;

public class RepartoImpl implements Reparto {
	private static int NUOVO_NUMERO_REPARTO = 0; // codice univoco che verr� assegnato all'eventuale nuovo reparto che (e se) verr� creato
	private final int numeroReparto;			 // codice univoco del reparto
	private final String nome;  				 // nome del reparto
	private int quantita; 						 // numero scorte presenti nel reparto
	private final int capacita;					 // capacit� del magazzino
	
	// costruttore
	public RepartoImpl(int c, String name) {
		this.numeroReparto 	  = RepartoImpl.NUOVO_NUMERO_REPARTO;
		this.nome 	  		  = "reparto_" + name;
		this.quantita 		  = 0;
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
		return this.quantita;
	}

	public int getCapacita() {
		return this.capacita;
	}

	public boolean isPresente() {
		return this.quantita > 0;
	}

	public boolean isPieno() {
		return this.quantita == this.capacita;
	}

	public boolean depositaScorte(int n) {
		// il deposito avviene solamente se il reparto non � pieno
		if (!this.isPieno()) {
			this.quantita++;
			return true;
		}
		return false;
	}

	public boolean prelevaScorte(int n) {
		// il prelievo avviene solamente se nel reparto sono presenti almeno n scorte
		if (n <= this.quantita) {
			this.quantita -= n;
			return true;
		}
		return false;
	}
	
}
