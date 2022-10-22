package model.classi;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import model.*;
import model.classi.exception.RepartoPienoException;
import model.classi.exception.SemilavoratiInsufficientiException;

public class OperaioImpl extends DipendenteImpl implements Operaio {
	
	private List<Costruzione> costruzioni;							// lista per tener conto delle costruzioni
	private List<Prelievo> prelievi;								// lista per tener conto dei prelievi
	private HashMap<String, Integer> semilavoratiMancanti;			/* mappa utilizzata quando non ci sono abbastanza semilavorati per
																	   la costruzione di un prodotto finito. stamperà i semilavorati mancanti */
	
	
	// costruttore
	public OperaioImpl(String n) {
		super(n);
		this.costruzioni			= new LinkedList<>();
		this.prelievi				= new LinkedList<>();
		this.semilavoratiMancanti 	= new HashMap<>();
	}
	
	public List<Costruzione> getProdottiCostruiti() {
		return new LinkedList<>(this.costruzioni);
	}

	public List<Prelievo> getSemilavoratiPrelevati() {
		return new LinkedList<>(this.prelievi);
	}

	public void costruisciProdottiFiniti(RepartoProdottiFiniti rep, int n, Date giorno) {
		if(rep.isPieno()) {
			throw new RepartoPienoException(rep.getQuantita(), rep.getCapacita());
		}else if(this.calcoloProdottiFinitiCostruibili(rep) < n) {
			throw new SemilavoratiInsufficientiException(semilavoratiMancanti);
		}else {
			for(int i = 0; i < n; i++) {
				ProdottoFinito pf = (ProdottoFinito)rep.depositaScorte();
				this.costruzioni.add(new CostruzioneImpl(this, pf, giorno));
				this.prelevaScorte(rep, giorno);
		    }
		}
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
		this.semilavoratiMancanti.clear();
		int prodottiCostruibili = 1000;
		HashMap<String, Integer> componenti = new HashMap<>(((ProdottoFinito)rep.getGiacenzaReparto()).getComponenti());
		List<String> listaComponenti = new LinkedList<>(componenti.keySet());
		for (int j = 0; j < rep.getListaRepartiSemilavorati().size(); j++) {
			for (int k = 0; k < listaComponenti.size(); k++) {
				if (rep.getListaRepartiSemilavorati().get(j).getGiacenzaReparto()
														    .getNome()
														    .equals(listaComponenti.get(k))) {
					
					if(rep.getListaRepartiSemilavorati().get(j).getQuantita() < componenti.get(listaComponenti.get(k))) {
						int numSemilavoratiMancanti = componenti.get(listaComponenti.get(k)) - rep.getListaRepartiSemilavorati().get(j).getQuantita();
						this.semilavoratiMancanti.put(listaComponenti.get(k), numSemilavoratiMancanti);
					}
					
					int costruzionePerSingoloSemi = rep.getListaRepartiSemilavorati().get(j).getQuantita() / componenti.get(listaComponenti.get(k));
					if (costruzionePerSingoloSemi < prodottiCostruibili) {
						prodottiCostruibili = costruzionePerSingoloSemi;
					}			
				}	
			}
		}	
		return prodottiCostruibili;
	}
	
	private void prelevaScorte(RepartoProdottiFiniti rep, Date g) {
		HashMap<String, Integer> componenti = new HashMap<>(((ProdottoFinito)rep.getGiacenzaReparto()).getComponenti());
		List<String> listaComponenti = new LinkedList<>(componenti.keySet());
		for (int j = 0; j < rep.getListaRepartiSemilavorati().size(); j++) {
			for (int k = 0; k < listaComponenti.size(); k++) {
				if (rep.getListaRepartiSemilavorati().get(j).getGiacenzaReparto()
														    .getNome()
														    .equals(listaComponenti.get(k))) {
					for (int i = 0; i < componenti.get(listaComponenti.get(k)); i++) {
						Semilavorato semi = (Semilavorato)rep.getListaRepartiSemilavorati().get(j).prelevaScorte();
						this.prelievi.add(new PrelievoImpl(this, semi, g));
					}
				}	
			}
		}			
	}
}
