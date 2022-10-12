package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OperaioImpl extends DipendenteImpl implements Operaio {
	
	private List<Costruzione> costruzioni;			// lista per tener conto delle costruzioni
	private List<Prelievo> prelievi;				// lista per tener conto dei prelievi
	
	// costruttore
	public OperaioImpl(String n) {
		super(n);
		this.costruzioni		= new LinkedList<>();
		this.prelievi			= new LinkedList<>();
	}
	
	public List<Costruzione> getProdottiCostruiti() {
		return new LinkedList<>(this.costruzioni);
	}

	public List<Prelievo> getSemilavoratiPrelevati() {
		return new LinkedList<>(this.prelievi);
	}

	public boolean costruisciProdottiFiniti(Reparto rep, int n, Operaio operaio, Date giorno) {
		HashMap<String, Integer> componenti = new HashMap<>(((ProdottoFinito)rep.getGiacenzaReparto())
																				.getComponenti());
		if(!rep.isPieno()){											// getListaRepartiSemilavorati
			for(int i = 0; i < n; i++) {
				ProdottoFinito pf = (ProdottoFinito)rep.depositaScorte();
				this.costruzioni.add(new CostruzioneImpl(operaio, pf, giorno));
				if(componenti.containsKey(rep.getListaRepartiSemilavorati.));
				
		    }
			return true;
		}else
			return false;
	}

	public int costruzioniPerProdottoFinito(ProdottoFinito pf) {
		return this.costruzioni.stream()
				   			   .filter(m -> m.getProdotto().getNome().equals(pf.getNome()))
				   			   .collect(Collectors.summingInt(m -> 1));
	}

	public int prelievoPerSemilavorato(Semilavorato sl) {
		return this.prelievi.stream()
							.filter(m -> m.getSemilavorato().getNome().equals(sl.getNome()))
							.collect(Collectors.summingInt(m -> 1));
	}

	public int calcoloProdottiFinitiCostruibili(Reparto rep) {
		// TODO Auto-generated method stub
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
