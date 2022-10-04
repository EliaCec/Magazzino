package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ResponsabileImpl implements Responsabile{
	
	private final String nomeCognome;
	private final int id;
	List<Vendita> prodottiVenduti;
	List<Deposita> semilavoratiDepositati;
	
	public ResponsabileImpl(String n, int i) {
		this.nomeCognome = n;
		this.id = i;
		this.prodottiVenduti = new LinkedList<>();
		this.semilavoratiDepositati = new LinkedList<>();
	}

	public String getNomeCognome() {
		return this.nomeCognome;
	}

	public int getId() {
		return this.id;
	}

	public List<ProdottoFinito> getProdottiVenduti() {
		return this.prodottiVenduti.stream()
				                   .map(v -> v.getProdottoFinito())
				                   .collect(Collectors.toList());
	}

	public List<Semilavorato> getSemilavoratiDepositati() {
		return this.semilavoratiDepositati.stream()
				                          .map(d -> d.getSemilavorato())
				                          .collect(Collectors.toList());
	}

	public boolean vendiProdottiFiniti(String prodottoFinito, int n, Responsabile responsabile, Date data) {
		for(int i = 0; i < n; i++) {
			ProdottoFinito pf = prelevaScorta(prodottoFinito);
			this.prodottiVenduti.add(new VenditaImpl(pf, responsabile, data));
		}
		return false;
	}

	public boolean depositaSemilavorati(Semilavorato semilavorato, int n, Responsabile responsabile, Date data) {	
		for(int i = 0; i < n; i++) {
			Semilavorato s = aggiungiScorta(semilavorato);
			this.semilavoratiDepositati.add(new DepositaImpl(semilavorato, responsabile, data));
		}
		return false;
	}

}
