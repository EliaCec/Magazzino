package model.classi;

import model.Dipendente;
import model.Dirigente;

public class DipendenteImpl implements Dipendente{
	
	private final String nome; 			// Nome del nuovo dipendente
	private final int id;				// Codice univoco dipendente

	// costruttore
	public DipendenteImpl(String n) {
		this.nome	= n;
		this.id 	= GeneratoreID.setGetIdDipendente();
	}
	
	public String getNomeCognome() {
		return this.nome;
	}
	
	public int getId() {
		if (this instanceof Dirigente) {
			return GeneratoreID.getIdDirigente();
		}
		return this.id;
	}
	
}

