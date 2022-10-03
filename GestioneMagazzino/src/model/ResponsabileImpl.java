package model;

import java.util.HashMap;
import java.util.LinkedList;

public class ResponsabileImpl implements Responsabile{

	public String getNomeCognome() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public LinkedList<ProdottoFinito> getProdottiVenduti() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Semilavorato> getSemilavoratiDepositati() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean vendiProdottiFiniti(HashMap<ProdottoFinito, Integer> prodottofinito, Responsabile responsabile,
			int giorno) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean depositaSemilavorati(HashMap<Semilavorato, Integer> semilavorato, Responsabile responsabile,
			int giorno) {
		// TODO Auto-generated method stub
		return false;
	}

}
