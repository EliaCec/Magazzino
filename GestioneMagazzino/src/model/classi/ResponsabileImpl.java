package model.classi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import model.*;
import model.classi.exception.ProdottiInsufficientiException;
import model.classi.exception.RepartoPienoException;

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

	public void vendiProdottiFiniti(RepartoProdottiFiniti reparto, int n, Date data) {   
		if(reparto.getQuantita() >= n) {
			for(int i = 0; i < n; i++) {
				ProdottoFinito pf = (ProdottoFinito)reparto.prelevaScorte();
				this.prodottiVenduti.add(new VenditaImpl(pf, this, data));
			}	
		}else{
			ProdottoFinito	pf = (ProdottoFinito) reparto.getGiacenzaReparto();
			throw new ProdottiInsufficientiException(pf.getNome() ,n - reparto.getQuantita());
		}
		
	}

	public void depositaSemilavorati(RepartoSemilavorati reparto, int n, Date data) {	
		if(!reparto.isPieno() && reparto.getQuantita() + n <= reparto.getCapacita()) {
			for(int i = 0; i < n; i++) {
				Semilavorato s = (Semilavorato)reparto.depositaScorte();
				this.semilavoratiDepositati.add(new DepositoImpl(s, this, data));
			}
		}else{
			throw new RepartoPienoException(reparto.getQuantita(), reparto.getCapacita());
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
