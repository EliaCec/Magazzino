package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Date;

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

public class TestResponsabile {
	
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
	public void testResponsabile() {
		
		// deposito semilavorati per test
	    l.depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(0), 8, new Date(2022, 10, 11, 8, 30));
		l.depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(1), 4, new Date(2022, 10, 11, 8, 30));
		l.depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(2), 6, new Date(2022, 10, 11, 8, 30));
		
		// aggiungo prodotti finiti ai reparti per test
		repArmadio.depositaScorte();
		for(int i = 0; i < 3; i++) 
			repMensola.depositaScorte();
		for(int i = 0; i < 2; i++) 
			repScrivania.depositaScorte();
		for(int i = 0; i < 4; i++) 
			repSedia.depositaScorte();
		
		// vendita prodotti finiti
	    l.vendiProdottiFiniti(repArmadio, 1, new Date(2022, 10, 11, 8, 30));
	    l.vendiProdottiFiniti(repMensola, 3, new Date(2022, 10, 11, 8, 30));
	    l.vendiProdottiFiniti(repScrivania, 2, new Date(2022, 10, 11, 8, 30));
	    l.vendiProdottiFiniti(repSedia, 4, new Date(2022, 10, 11, 8, 30));
	    
		// vendite per tipologia
		assertEquals(1, l.venditaPerTipologia("armadio"));
		assertEquals(3, l.venditaPerTipologia("mensola"));
		assertEquals(2, l.venditaPerTipologia("scrivania"));
		assertEquals(4, l.venditaPerTipologia("sedia"));
		
		// numero semilavorati depositati
		assertEquals(8, l.depositoPerSemilavorato("anta_armadio"));
		assertEquals(6, l.depositoPerSemilavorato("pannello_piccolo_armadio"));
		assertEquals(4, l.depositoPerSemilavorato("pannello_grande_armadio"));
		
		// controllo che i prodotti finiti venduti e i semilavorati depositati di un altro operaio siano vuoti
	    assertEquals(0, m.venditaPerTipologia("armadio"));
		assertEquals(0, m.depositoPerSemilavorato("anta_armadio"));
		
		// controllo presenza di tutti i prodotti venduti in lista
				assertEquals(10, l.getProdottiVenduti().size());        // ARMADIO:	    1
																		// MENSOLA:     3
																		// SCRIVANIA:	2
																		// SEDIA:       4
				
		// controllo presenza di tutti i semilavorati depositati in lista
		assertEquals(18, l.getSemilavoratiDepositati().size());        // ANTA ARMADIO:               8
																	   // PANNELLO ARMADIO PICCOLO:   6
																	   // PANNELLO ARMADIO GRANDE:    4
	} 
}
