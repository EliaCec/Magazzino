package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Magazzino;
import model.Operaio;
import model.ProdottoFinito;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.Semilavorato;
import model.classi.MagazzinoImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.exception.RepartoPienoException;
import model.classi.exception.SemilavoratiInsufficientiException;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

class TestOperaio {
	
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
	@Test
	void testOperaio() {
		// aggiunti semilavorati per test
		l.depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(0), 8,  new Date(2022, 10, 11, 7, 30));
		l.depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(1), 4,  new Date(2022, 10, 11, 7, 40));
		l.depositaSemilavorati(repArmadio.getListaRepartiSemilavorati().get(2), 16,  new Date(2022, 10, 11, 7, 50));
		
		// test su quanti prodotti posso costruirci
		assertEquals(4, d.calcoloProdottiFinitiCostruibili(repArmadio));
		
		// costruisco 2 armadi
		d.costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 7, 55));
		assertEquals(2, repArmadio.getQuantita());
		
		// controllo ora quanti prodotti posso costruire
		assertEquals(2, d.calcoloProdottiFinitiCostruibili(repArmadio));
		
		// controllo quanti pannelli grandi ho utilizzato (get(2))
		assertEquals(8, d.prelievoPerSemilavorato((Semilavorato)repArmadio.getListaRepartiSemilavorati()
																		  .get(2)
																		  .getGiacenzaReparto()));
		
		// controllo prodotti finiti costruiti
		assertEquals(2, d.costruzioniPerProdottoFinito((ProdottoFinito)repArmadio.getGiacenzaReparto()));
		
		// controllo che i prodotti finiti costruiti e i semilavorati prelevati di un altro operaio siano vuoti
		assertEquals(0, f.prelievoPerSemilavorato((Semilavorato)repArmadio.getListaRepartiSemilavorati()
				  														  .get(2)
				  														  .getGiacenzaReparto()));
		assertEquals(0, f.costruzioniPerProdottoFinito((ProdottoFinito)repArmadio.getGiacenzaReparto()));
		
		// aggiunti semilavorati per test
		l.depositaSemilavorati(repScrivania.getListaRepartiSemilavorati().get(0), 30,  new Date(2022, 10, 12, 7, 30));
		l.depositaSemilavorati(repScrivania.getListaRepartiSemilavorati().get(1), 80,  new Date(2022, 10, 12, 7, 40));
		
		// controllo prodotti costruibili
		assertEquals(20, d.calcoloProdottiFinitiCostruibili(repScrivania));
		
		// costruisco 10 scrivanie
		d.costruisciProdottiFiniti(repScrivania, 10, new Date(2022, 10, 12, 7, 55));
		assertEquals(10, repScrivania.getQuantita());
		
		// controllo presenza di tutti i prodotti prelevati in lista
		assertEquals(64, d.getSemilavoratiPrelevati().size());  // ARMADIO:		8 + 2 + 4 = 14
																// SCRIVANIA:	10 + 40 = 50
		
		// controllo presenza di tutti i prodotti costruiti in lista
		assertEquals(12, d.getProdottiCostruiti().size()); // 10 scrivanie e 2 armadi
		
		// controllo correttezza exception quando non ho abbastanza semilavorati per costruire un prodotto finito
		assertThrows(SemilavoratiInsufficientiException.class, () -> d.costruisciProdottiFiniti(repSedia, 1, new Date(2022, 10, 13, 7, 40)));
		
		// aggiungo tanti prodotti finiti quanto è la capacità massima del reparto per un successivo test
		for(int i = 0; i < repSedia.getCapacita(); i++) {
			repSedia.depositaScorte();
		}
		
		// controllo correttezza exception quando non ho abbastanza semilavorati per costruire un prodotto finito
		assertThrows(RepartoPienoException.class, () -> d.costruisciProdottiFiniti(repSedia, 1, new Date(2022, 10, 14, 7, 40)));
	}
}
