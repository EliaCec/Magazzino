package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ResponsabileImpl implements Responsabile{
	
	private final String nomeCognome;                   // nome e cognome del responsabile
	private final int id;                               // ID del responsabile
	List<Vendita> prodottiVenduti;                      // lista dei prodotti venduti dal responsabile in un dato momento
	List<Deposito> semilavoratiDepositati;              // lista dei semilavorati depositati dal reponsabile in un dato momento
	
	// costruttore 
	public ResponsabileImpl(String n, int i) {
		this.nomeCognome            = n;
		this.id                     = i;
		this.prodottiVenduti        = new LinkedList<>();
		this.semilavoratiDepositati = new LinkedList<>();
	}

	public String getNomeCognome() {
		return this.nomeCognome;
	}

	public int getId() {
		return this.id;
	}

	public List<Vendita> getProdottiVenduti() {
		return new LinkedList<>(this.prodottiVenduti);
	}

	public List<Deposito> getSemilavoratiDepositati() {
		return new LinkedList<>(this.semilavoratiDepositati);
	}

	public boolean vendiProdottiFiniti(Reparto reparto, int n, Responsabile responsabile, Date data) {
		if(reparto.getQuantita() >= n) {
			for(int i = 0; i < n; i++) {
				ProdottoFinito pf = (ProdottoFinito)reparto.prelevaScorte();
				this.prodottiVenduti.add(new VenditaImpl(pf, responsabile, data));
			}		
			return true;
		}else{
			return false;
		}
		
	}

	public boolean depositaSemilavorati(Reparto reparto, int n, Responsabile responsabile, Date data) {	
		if(!reparto.isPieno() || reparto.getQuantita() + n <= reparto.getCapacita()) {
			for(int i = 0; i < n; i++) {
				Semilavorato s = (Semilavorato)reparto.depositaScorte();
				this.semilavoratiDepositati.add(new DepositoImpl(s, responsabile, data));
		    }
			return true;
		
		}else{
			return false;
		}
		
	}
}
