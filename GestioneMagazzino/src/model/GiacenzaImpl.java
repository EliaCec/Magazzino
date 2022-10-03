package model;

public class GiacenzaImpl implements Giacenza {
	
	private final String nome; 			// Nome della giacenza
	private final int id;				// Codice univoco giacenza
	private final String tipo;			// Tipo di giacenza (semilavorato o prodotto finito)

	// costruttore
	public GiacenzaImpl(String n, int i, String t) {
		this.nome	= n;
		this.id 	= i;
		this.tipo 	= t;
	}
	
	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}

	public String getTipo() {
		return this.tipo;
	}

}
