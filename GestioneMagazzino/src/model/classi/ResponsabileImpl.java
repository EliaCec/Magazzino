package model.classi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import model.*;

public class ResponsabileImpl extends DipendenteImpl implements Responsabile{
	
	List<Vendita> prodottiVenduti;                      // lista dei prodotti venduti dal responsabile in un dato momento
	List<Deposito> semilavoratiDepositati;              // lista dei semilavorati depositati dal reponsabile in un dato momento
	
	// costruttore 
	public ResponsabileImpl(String n) {
		super(n);
		this.prodottiVenduti        = new LinkedList<>();
		this.semilavoratiDepositati = new LinkedList<>();
	}

	public List<Vendita> getProdottiVenduti() {
		return new LinkedList<>(this.prodottiVenduti);
	}

	public List<Deposito> getSemilavoratiDepositati() {
		return new LinkedList<>(this.semilavoratiDepositati);
	}

	public boolean vendiProdottiFiniti(RepartoSemilavorati reparto, int n, Responsabile responsabile, Date data) {
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

	public boolean depositaSemilavorati(RepartoSemilavorati reparto, int n, Responsabile responsabile, Date data) {	
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
	
	public int venditaPerTipologia(String pf) {
		return this.prodottiVenduti.stream()
				                   .filter(p -> p.getProdottoFinito().getNome().equals(pf))
				                   .collect(Collectors.summingInt(p -> 1));
	}
	
	public int depositoPerSemilavorato(String sl) {
		return this.semilavoratiDepositati.stream()
				                          .filter(d -> d.getSemilavorato().getNome().equals(sl))
				                          .collect(Collectors.summingInt(d -> 1));
				              
	}
}
