package model;

public class GiacenzaImpl implements Giacenza {
	
	private static int NUOVOID = 0;		// Codice univoco per le nuove giacenze
	private final String nome; 			// Nome della giacenza
	private final int id;				// Codice univoco giacenza
	private final String tipo;			// Tipo di giacenza (semilavorato o prodotto finito)

	// costruttore
	public GiacenzaImpl(String n, String t) {
		this.nome	= n;
		this.id 	= GiacenzaImpl.NUOVOID;
		this.tipo 	= t;
		GiacenzaImpl.NUOVOID++;
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
