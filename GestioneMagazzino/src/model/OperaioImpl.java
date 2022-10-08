package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OperaioImpl implements Operaio {
	
	private final String nome_cognome;
	private final int id;
	private List<Costruzione> costruzioni;
	private List<Prelievo> prelievi;
	
	// costruttore
	public OperaioImpl(String n, int id) {
		this.nome_cognome 		= n;
		this.id 				= id;
		this.costruzioni		= new LinkedList<>();
		this.prelievi			= new LinkedList<>();
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
		return this.prelievi.stream()
							.map(m -> m.getSemilavorato())
							.collect(Collectors.toList());
	}

	public boolean costruisciProdottiFiniti(Reparto rep, Operaio operaio, Date giorno) {
		if(!rep.isPieno()){
			
			return true;
		}else
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
		return this.prelievi.stream()
							.map(m -> m.getSemilavorato())
							.filter(m -> m.getNome().equals(sl.getNome()))
							.collect(Collectors.summingInt(m -> 1));
	}

	public int calcoloProdottiFinitiCostruibili(Reparto rep) {
		
		return 0;
	}
	
	private List<String> componentiSemi(Reparto rep) {
		HashMap<String, Integer> componenti = new HashMap<>(((ProdottoFinito)rep.getGiacenzaReparto()).getComponenti());
		List<String> listaComponenti = new LinkedList<>(componenti.keySet());
		List<Integer> listaQuantit‡Componenti = new LinkedList<>(componenti.values());
		List<String> totSemi = new LinkedList<>();
		for (int j = 0; j < listaQuantit‡Componenti.size(); j++) {
			for (int i = 0; i < listaQuantit‡Componenti.get(j); i++) {
				totSemi.add(listaComponenti.get(j));
			}
		}			
		return totSemi;
	}

}
