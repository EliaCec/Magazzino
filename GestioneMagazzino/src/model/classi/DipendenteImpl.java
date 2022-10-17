package model.classi;

import model.Dipendente;

public class DipendenteImpl implements Dipendente{
	
	private static int NUOVOID = 0;		// Codice univoco per i nuovi dipendenti
	private final String nome; 			// Nome del nuovo dipendente
	private final int id;				// Codice univoco dipendente

	// costruttore
	public DipendenteImpl(String n) {
		this.nome	= n;
		this.id 	= DipendenteImpl.NUOVOID;
		DipendenteImpl.NUOVOID++;
	}
	
	public String getNomeCognome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}
}

