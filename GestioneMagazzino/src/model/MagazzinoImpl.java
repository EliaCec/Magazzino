package model;

import java.util.Date;
import java.util.LinkedList;

public class MagazzinoImpl implements Magazzino {

	public int getNumeroReparti() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public LinkedList<ProdottoFinito> storicoVenditeGiornaliero(Date giorno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LinkedList<ProdottoFinito> storicoCostruzioniGiornaliero(Date giorno) {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Semilavorato> storicoSemilavoratiUsatiGiornaliero(Date giorno) {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Responsabile> getResponsabiliAttivi() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Operaio> getOperaiAttivi() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean cambioTurno(LinkedList<Operaio> operai, LinkedList<Responsabile> responsabili, Date giorno) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
