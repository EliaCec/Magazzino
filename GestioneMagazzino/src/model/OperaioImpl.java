package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OperaioImpl implements Operaio {
	
	private final String nome_cognome;
	private final int id;
	private List<Semilavorato> semilavoratiUsati;
	private List<Costruzione> costruzioni;
	
	// costruttore
	public OperaioImpl(String n, int id) {
		this.nome_cognome 		= n;
		this.id 				= id;
		this.semilavoratiUsati	= new LinkedList<>();
	}

	public String getNomeCognome() {
		return this.nome_cognome;
	}

	public int getId() {
		return this.id;
	}

	public List<ProdottoFinito> getProdottiCostruiti() {
		return this.costruzioni.stream()
							   .map(m -> m.getProdotto())
							   .collect(Collectors.toList());
	}

	public List<Semilavorato> getSemilavoratiPrelevati() {
		return this.costruzioni.stream()
				   .map(m -> m.getProdotto())
				   .collect(Collectors.toList()).stream()
				   								.map(m -> m.getComponenti())
				   								.;
	}

	public boolean costruisciProdottiFiniti(ProdottoFinito pf, Operaio operaio, Date giorno) {
		HashMap<String, Integer> componenti = new HashMap<>(pf.getComponenti());
		this.
		return false;
	}

	public int costruzioniPerProdottoFinito(ProdottoFinito pf) {
		return this.costruzioni.stream()
				   			   .map(m -> m.getProdotto())
				   			   .collect(Collectors.toList()).stream()
				   											.filter(m -> m.equals(pf))
				   											.collect(Collectors.summingInt(m -> 1));
	}

	public int prelievoPerSemilavorato(Semilavorato sl) {
		return 
	}

	public int calcoloProdottiFinitiCostruibili(ProdottoFinito pf) {
		// TODO Auto-generated method stub
		return 0;
	}

}
