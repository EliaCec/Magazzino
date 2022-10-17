package model.classi;

import model.Giacenza;

public class GiacenzaImpl implements Giacenza {
	
	private static int NUOVOID = 0;		// Codice univoco per le nuove giacenze
	private final String nome; 			// Nome della giacenza
	private final int id;				// Codice univoco giacenza

	// costruttore
	public GiacenzaImpl(String n) {
		this.nome	= n;
		this.id 	= GiacenzaImpl.NUOVOID;
		GiacenzaImpl.NUOVOID++;
	}
	
	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}

}
