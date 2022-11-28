package model.classi;

import model.Giacenza;

public class GiacenzaImpl implements Giacenza {
	
	private final String nome; 			// Nome della giacenza
	private final int id;				// Codice univoco giacenza

	// costruttore
	public GiacenzaImpl(String n) {
		this.nome	= n;
		this.id 	= GeneratoreID.setGetIdGiacenza();
	}
	
	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}

}
