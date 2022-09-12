package model;

import java.util.Date;
import java.util.LinkedList;

public interface Magazzino {
	public int getNumeroReparti();
	public LinkedList<ProdottoFinito> storicoVenditeGiornaliero(Date giorno);
	public LinkedList<ProdottoFinito> storicoCostruzioniGiornaliero(Date giorno);
	public LinkedList<Semilavorato> storicoSemilavoratiUsatiGiornaliero(Date giorno);
	public LinkedList<Responsabile> getResponsabiliAttivi();
	public LinkedList<Operaio> getOperaiAttivi();
	public boolean cambioTurno(LinkedList<Operaio> operai, 
							   LinkedList<Responsabile> responsabili,
							   Date giorno);
}
