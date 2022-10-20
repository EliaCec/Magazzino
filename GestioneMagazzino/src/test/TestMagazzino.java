package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.*;
import model.classi.*;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

class TestMagazzino {

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
	
	@SuppressWarnings("deprecation")
	@Test
	public void testResponsabile() {
		
		// vendita prodotti finiti
	    mag.getResponsabiliAttivi().get(0).vendiProdottiFiniti(repArmadio, 1, new Date(2022, 10, 11, 8, 30));
	    mag.getResponsabiliAttivi().get(0).vendiProdottiFiniti(repMensola, 2, new Date(2022, 10, 11, 8, 30));
	    mag.getResponsabiliAttivi().get(0).vendiProdottiFiniti(repSedia, 2, new Date(2022, 10, 11, 8, 30));
	    mag.getResponsabiliAttivi().get(0).vendiProdottiFiniti(repScrivania, 1, new Date(2022, 10, 11, 8, 30));
	    
	    // deposito semilavorati
	    mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(0), 2, new Date(2022, 10, 11, 8, 30));
		mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repMensola.getListaRepartiSemilavorati().get(1), 6, new Date(2022, 10, 11, 8, 30));
		mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repSedia.getListaRepartiSemilavorati().get(0), 5, new Date(2022, 10, 11, 8, 30));
		mag.getResponsabiliAttivi().get(0).depositaSemilavorati(repScrivania.getListaRepartiSemilavorati().get(0), 4, new Date(2022, 10, 11, 8, 30));
		
		// vendite per tipologia
		assertTrue(mag.getResponsabiliAttivi().get(0).venditaPerTipologia("armadio") == 1);
		/*mag.getResponsabiliAttivi().get(0).venditaPerTipologia("mensola");
		mag.getResponsabiliAttivi().get(0).venditaPerTipologia("sedia");
		mag.getResponsabiliAttivi().get(0).venditaPerTipologia("scrivania");*/
		
		// numero semilavorati depositati
		assertTrue(mag.getResponsabiliAttivi().get(0).depositoPerSemilavorato("anta_armadio") == 2);
		assertTrue(mag.getResponsabiliAttivi().get(0).depositoPerSemilavorato("ripiano_mensola") == 6);
		assertTrue(mag.getResponsabiliAttivi().get(0).depositoPerSemilavorato("schienale_sedia") == 5);
		assertTrue(mag.getResponsabiliAttivi().get(0).depositoPerSemilavorato("pianale_scrivania") == 4);   
	}

}
