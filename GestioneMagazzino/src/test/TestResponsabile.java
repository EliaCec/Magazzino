package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Date;

import model.Dirigente;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.DirigenteImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.exception.ProdottiInsufficientiException;
import model.classi.exception.RepartoPienoException;
import model.classi.reparti.NomiReparti;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

public class TestResponsabile {
	
	Dirigente dir = new DirigenteImpl();
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
	void testResponsabile() {
		
		// deposito semilavorati per test
	    l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_ANTA_ARMADIO.getNome(), repArmadio.getListaRepartiSemilavorati()), 8, new Date(2022, 10, 11, 8, 30));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO.getNome(), repArmadio.getListaRepartiSemilavorati()), 16, new Date(2022, 10, 11, 8, 30));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO.getNome(), repArmadio.getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
		
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
	    
		// verifico che le vendite siano avvenute grazie a vendite per tipologia
		assertEquals(1, l.venditaPerTipologia("armadio"));
		assertEquals(3, l.venditaPerTipologia("mensola"));
		assertEquals(2, l.venditaPerTipologia("scrivania"));
		assertEquals(4, l.venditaPerTipologia("sedia"));
		
		// verifico che il numero semilavorati depositati sia corretto grazie a deposita per semilavorato
		assertEquals(8, l.depositoPerSemilavorato("anta_armadio"));
		assertEquals(16, l.depositoPerSemilavorato("pannello_piccolo_armadio"));
		assertEquals(4, l.depositoPerSemilavorato("pannello_grande_armadio"));
		
		// controllo che i prodotti finiti venduti e i semilavorati depositati di un altro operaio siano vuoti
	    assertEquals(0, m.venditaPerTipologia("armadio"));
		assertEquals(0, m.depositoPerSemilavorato("anta_armadio"));
		
		// controllo correttezza exception quando non ho un prodotto finito da vendere
				assertThrows(ProdottiInsufficientiException.class, () -> l.vendiProdottiFiniti(repSedia, 1, new Date(2022, 10, 13, 9, 40)));
				
		// controllo correttezza exception quando il reparto di semilavorati è pieno
				assertThrows(RepartoPienoException.class, () -> l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_GAMBA_SEDIA.getNome(), repSedia.getListaRepartiSemilavorati()), 351, new Date(2022, 10, 11, 9, 40)));
				
		// controllo presenza di tutti i prodotti venduti in lista
				assertEquals(10, l.getProdottiVenduti().size());        // ARMADIO:	    1
																		// MENSOLA:     3
																		// SCRIVANIA:	2
																		// SEDIA:       4
				
		// controllo presenza di tutti i semilavorati depositati in lista
		assertEquals(28, l.getSemilavoratiDepositati().size());        // ANTA ARMADIO:               8
																	   // PANNELLO ARMADIO PICCOLO:   6
																	   // PANNELLO ARMADIO GRANDE:    4
	} 
}
