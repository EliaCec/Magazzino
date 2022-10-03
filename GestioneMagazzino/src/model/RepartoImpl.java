package model;

public class RepartoImpl implements Reparto {
	private final int id;			// codice univoco del reparto
	private final String nome;  	// nome del reparto
	private int quantita; 			// numero scorte presenti nel reparto
	private final int capacita;		// capacità del magazzino
	
	// costruttore
	public RepartoImpl(int c, Giacenza g) {
		this.id 	  = 100 + g.getId(); // l'id del reparto è uguale all'id della giacenza che viene stoccata aumentata di 100
		this.nome 	  = "reparto_" + g.getNome();
		this.quantita = 0;
		this.capacita = c;
	}

	public int getId() {
		return this.id;
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
		// il deposito avviene solamente se il reparto non è pieno
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
