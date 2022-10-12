package model;

import java.util.List;

public interface RepartoProdottiFiniti extends RepartoSemilavorati {
	
	/* Metodo che restituisce la lista di tutti i reparti dove sono contenuti i semilavorati necessari per la costruzione */
	public List<RepartoSemilavorati> getListaRepartiSemilavorati();
	
}
