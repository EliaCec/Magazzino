package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Magazzino;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.MagazzinoImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.reparti.NomiReparti;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

public class TestGenerale {
	Magazzino mag = new MagazzinoImpl();
	Operaio d = new OperaioImpl("Daniele_Rossi");
	Operaio f = new OperaioImpl("Fausto_Bianchi");
	Operaio t = new OperaioImpl("Tommaso_Nardelli");
	Operaio a = new OperaioImpl("Alberto_Antonelli");
	Responsabile l = new ResponsabileImpl("Luca_Amadori");
	Responsabile m = new ResponsabileImpl("Matteo_Pellizzari");
	RepartoProdottiFiniti repArmadio = new RepartoArmadio();
	RepartoProdottiFiniti repScrivania = new RepartoScrivania();
	RepartoProdottiFiniti repSedia = new RepartoSedia();
	RepartoProdottiFiniti repMensola = new RepartoMensola();
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGenerale() {
		// creazione reparti
		mag.creaReparto(repArmadio);
		mag.creaReparto(repMensola);
		mag.creaReparto(repScrivania);
		mag.creaReparto(repSedia);
		
		// assunzione di quattro operai
		mag.assumiDipendente(d);
		mag.assumiDipendente(f);
		mag.assumiDipendente(t);
		mag.assumiDipendente(a);
		
		// assunzione due responsabili
		mag.assumiDipendente(l);
		mag.assumiDipendente(m);
		
		// attivo operai e responsabili
		List<Operaio> operai = new LinkedList<>();
		operai.add(d);
		operai.add(f);
		operai.add(t);
		operai.add(a);
		List<Responsabile> responsabili = new LinkedList<>();
		responsabili.add(l);
		responsabili.add(m);
		mag.cambioTurno(operai, responsabili, new Date(2022, 10, 11, 7, 30));
		
		// deposito semilavorati per test
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).depositaSemilavorati(mag.cercaRepartoPerNome(NomiReparti.REPARTO_ANTA_ARMADIO.getNome(), ((RepartoProdottiFiniti) mag.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), mag.getReparti())).getListaRepartiSemilavorati()), 8, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).depositaSemilavorati(mag.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO.getNome(), ((RepartoProdottiFiniti) mag.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), mag.getReparti())).getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).depositaSemilavorati(mag.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO.getNome(), ((RepartoProdottiFiniti) mag.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), mag.getReparti())).getListaRepartiSemilavorati()), 16, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).depositaSemilavorati(mag.cercaRepartoPerNome(NomiReparti.REPARTO_GAMBA_SCRIVANIA.getNome(), ((RepartoProdottiFiniti) mag.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(), mag.getReparti())).getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).depositaSemilavorati(mag.cercaRepartoPerNome(NomiReparti.REPARTO_PIANALE_SCRIVANIA.getNome(), ((RepartoProdottiFiniti) mag.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(), mag.getReparti())).getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
	    
	    // costruisco prodotti finiti
	    ((Operaio) mag.cercaDipendentePerNome("Daniele_Rossi", mag.getOperaiAttivi())).costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 00));
	    ((Operaio) mag.cercaDipendentePerNome("Daniele_Rossi", mag.getOperaiAttivi())).costruisciProdottiFiniti(repScrivania, 1, new Date(2022, 10, 11, 9, 00));
	    
		// vendita prodotti finiti
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).vendiProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 30));
	    ((Responsabile) mag.cercaDipendentePerNome("Luca_Amadori", mag.getResponsabiliAttivi())).vendiProdottiFiniti(repScrivania, 1, new Date(2022, 10, 11, 9, 45));
	   
	    
		// test controllo vendite giornaliere	
	    System.out.println(mag.storicoVenditeGiornaliero(new Date(2022, 10, 11, 9,30)).size());
		assertEquals(9, mag.storicoVenditeGiornaliero(new Date(2022, 10, 11,9,30)).size());

		/*
		// test controllo costruzioni giornaliere
		assertEquals(1, mag.storicoCostruzioniGiornaliero(new Date(2022, 10, 11)).size());
		
		// test controllo semilavorati usati in un giorno
		assertEquals(4, mag.storicoSemilavoratiUsatiGiornaliero(new Date(2022, 10, 11)).size());*/
		
		
	}
}
