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

	public boolean costruisciProdottiFiniti(RepartoProdottiFiniti rep, int n, Operaio operaio, Date giorno) {
		if(!rep.isPieno()){
			for(int i = 0; i < n; i++) {
				ProdottoFinito pf = (ProdottoFinito)rep.depositaScorte();
				this.costruzioni.add(new CostruzioneImpl(operaio, pf, giorno));
				this.prelevaScorte(rep, operaio, giorno);
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

	public int calcoloProdottiFinitiCostruibili(RepartoProdottiFiniti rep) {
		int prodottiCostruibili = 1000;
		HashMap<String, Integer> componenti = new HashMap<>(((ProdottoFinito)rep.getGiacenzaReparto()).getComponenti());
		List<String> listaComponenti = new LinkedList<>(componenti.keySet());
		for (int j = 0; j < rep.getListaRepartiSemilavorati().size(); j++) {
			for (int k = 0; k < listaComponenti.size(); k++) {
				if (rep.getListaRepartiSemilavorati().get(j).getGiacenzaReparto()
														    .getNome()
														    .equals(listaComponenti.get(k))) {
					int costruzionePerSingoloSemi = rep.getListaRepartiSemilavorati().get(j).getQuantita() / componenti.get(listaComponenti.get(k));
					if (costruzionePerSingoloSemi < prodottiCostruibili) {
						prodottiCostruibili = costruzionePerSingoloSemi;
					}			
				}	
			}
		}	
		return prodottiCostruibili;
	}
	
	private void prelevaScorte(RepartoProdottiFiniti rep, Operaio op, Date g) {
		HashMap<String, Integer> componenti = new HashMap<>(((ProdottoFinito)rep.getGiacenzaReparto()).getComponenti());
		List<String> listaComponenti = new LinkedList<>(componenti.keySet());
		for (int j = 0; j < rep.getListaRepartiSemilavorati().size(); j++) {
			for (int k = 0; k < listaComponenti.size(); k++) {
				if (rep.getListaRepartiSemilavorati().get(j).getGiacenzaReparto()
														    .getNome()
														    .equals(listaComponenti.get(k))) {
					for (int i = 0; i < componenti.get(listaComponenti.get(k)); i++) {
						Semilavorato semi = (Semilavorato)rep.getListaRepartiSemilavorati().get(j).prelevaScorte();
						this.prelievi.add(new PrelievoImpl(op, semi, g));
					}
				}	
			}
		}			
	}
}
