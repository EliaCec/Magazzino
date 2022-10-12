package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.reparti.NomiReparti;
import model.semilavorati.AntaArmadio;
import model.semilavorati.BraccioloSedia;
import model.semilavorati.GambaSedia;
import model.semilavorati.PannelloGrandeArmadio;
import model.semilavorati.PannelloPiccoloArmadio;
import model.semilavorati.PianaleScrivania;
import model.semilavorati.RipianoMensola;
import model.semilavorati.SchienaleSedia;
import model.semilavorati.SedutaSedia;
import model.semilavorati.StaffaMensola;

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

	public List<Costruzione> getProdottiCostruiti() {
		return new LinkedList<>(this.costruzioni);
	}

	public List<Prelievo> getSemilavoratiPrelevati() {
		return new LinkedList<>(this.prelievi);
	}

	public boolean costruisciProdottiFiniti(RepartoSemilavorati rep, Operaio operaio, Date giorno) {
		if(!rep.isPieno()){
			
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

	public int calcoloProdottiFinitiCostruibili(RepartoSemilavorati rep) {
		
		return 0;
	}
	
	private List<String> componentiSemi(RepartoSemilavorati rep) {
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
