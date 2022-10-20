package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Magazzino;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.MagazzinoImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

class TestReparti {
	
	// inizializzazione prima di eseguire un qualunque test
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
	@Before
	// metodo eseguito prima di ogni test
	public void creaMagazzino() {
		// assunzione operai
		mag.assumiDipendente(d);
		mag.assumiDipendente(f);
		mag.assumiDipendente(l);
		mag.assumiDipendente(a);
		// assunzione responsabili
		mag.assumiDipendente(t);
		mag.assumiDipendente(m);
		// creazione reparti
		mag.creaReparto(repArmadio);
		mag.creaReparto(repScrivania);
	    mag.creaReparto(repSedia);
		mag.creaReparto(repMensola);
		// avvio giornata
		List<Operaio> operaiAttivi = new LinkedList<>();
		operaiAttivi.add(a);
		operaiAttivi.add(f);
		List<Responsabile> responsabiliAttivi = new LinkedList<>();
		responsabiliAttivi.add(l);
		mag.cambioTurno(operaiAttivi, responsabiliAttivi, new Date(2022, 10, 11, 7, 30));
		// deposito semilavorati
		mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(0), 8, new Date(2022, 10, 11, 8, 00));
		mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(1), 4, new Date(2022, 10, 11, 8, 00));
		mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(2), 16, new Date(2022, 10, 11, 8, 00));
		// costruzione prodottifiniti
		mag.getOperaiAttivi().get(0).costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 8, 30));
	}
		
	@Test
	// metodo che testa il deposito delle scorte in un reparto
	public void testDepositiReparto() {
		assertEquals(0, repSedia.getQuantita()); // verifica reparto vuoto
		assertFalse(repSedia.isPresente()); // verifica scorta non presente
		
		// DEPOSITO fino a riempire il magazzino scorte
		for (int i = 0; i < repSedia.getCapacita(); i++)
			repSedia.depositaScorte();
		
		assertTrue(repSedia.isPresente()); // verifica presenza di almeno una scorta
		assertEquals(repSedia.getCapacita(), repSedia.getQuantita()); // verifica quantità corretta di scorte
		assertTrue(repSedia.isPieno()); // verifica reparto pieno
	}
	
	@Test
	public void testPrelieviReparto() {
		/* AGGIUNGERE ECCEZIONE PRELIEVO SCORTE IN CASO DI MAGAZZINO VUOTO */
		// deposito di 5 scorte
		for (int i = 0; i < 60; i++)
			repSedia.depositaScorte();
		
		// PRELIEVO di 4 le scorte eccetto una
		for (int i = 0; i < 45; i++)
			repSedia.prelevaScorte();
		
		assertEquals(15, repSedia.getQuantita()); // verifica quantità corretta di scorte
		assertFalse(repSedia.isPieno()); // verifica reparto non pieno
		
		// PRELIEVO DI TUTTE LE SCORTE RIMASTE
		while (repSedia.getQuantita() > 0)
			repSedia.prelevaScorte();
		
		// PRELIEVO di una scorte non presente
		
	}

}
