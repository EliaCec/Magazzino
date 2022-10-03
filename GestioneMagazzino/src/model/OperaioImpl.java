package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class OperaioImpl implements Operaio {

	public String getNomeCognome() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public LinkedList<ProdottoFinito> getProdottiCostruiti() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Semilavorato> getSemilavoratiPrelevati() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean costruisciProdottiFiniti(HashMap<ProdottoFinito, Integer> pf, Operaio operaio, Date giorno) {
		// TODO Auto-generated method stub
		return false;
	}

	public int costruzioniPerProdottoFinito(ProdottoFinito pf) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int prelievoPerSemilavorato(Semilavorato sl) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int calcoloProdottiFinitiCostruibili(ProdottoFinito pf) {
		// TODO Auto-generated method stub
		return 0;
	}

}
