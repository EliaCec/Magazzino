package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Dirigente;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.DirigenteImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.exception.RepartoPienoException;
import model.classi.exception.SemilavoratiInsufficientiException;
import model.classi.reparti.NomiReparti;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

class TestOperaio {
	
	// inizializzazione prima di eseguire un qualunque test
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
	void testOperaio() {
		// aggiunti semilavorati per test
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_ANTA_ARMADIO.getNome(), repArmadio.getListaRepartiSemilavorati()), 8, new Date(2022, 10, 11, 7, 30));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO.getNome(), repArmadio.getListaRepartiSemilavorati()), 16, new Date(2022, 10, 11, 7, 40));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO.getNome(), repArmadio.getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 7, 50));
		
		// test su quanti prodotti posso costruirci
		assertEquals(4, d.calcoloProdottiFinitiCostruibili(repArmadio, 0));
		
		// costruisco 2 armadi
		d.costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 7, 55));
		assertEquals(2, repArmadio.getQuantita());
		
		// controllo ora quanti prodotti posso costruire
		assertEquals(2, d.calcoloProdottiFinitiCostruibili(repArmadio, 0));
		
		// controllo quanti pannelli grandi ho utilizzato (get(2))
		assertEquals(8, d.prelievoPerSemilavorato(repArmadio.getListaRepartiSemilavorati()
															.get(2)
															.getGiacenzaReparto().getNome()));
		
		// controllo prodotti finiti costruiti
		assertEquals(2, d.costruzioniPerProdottoFinito(repArmadio.getGiacenzaReparto().getNome()));
		
		// controllo che i prodotti finiti costruiti e i semilavorati prelevati di un altro operaio siano vuoti
		assertEquals(0, f.prelievoPerSemilavorato(repArmadio.getListaRepartiSemilavorati()
				  											.get(2)
				  											.getGiacenzaReparto().getNome()));
		assertEquals(0, f.costruzioniPerProdottoFinito(repArmadio.getGiacenzaReparto().getNome()));
		
		// aggiunti semilavorati per test
		l.depositaSemilavorati(repScrivania.getListaRepartiSemilavorati().get(0), 30,  new Date(2022, 10, 12, 7, 30));
		l.depositaSemilavorati(repScrivania.getListaRepartiSemilavorati().get(1), 80,  new Date(2022, 10, 12, 7, 40));
		
		// controllo prodotti costruibili
		assertEquals(20, d.calcoloProdottiFinitiCostruibili(repScrivania, 0));
		
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
		// aggiungo semilavorati per la costruzione di una sedia nel successivo test
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCHIENALE_SEDIA.getNome(), repSedia.getListaRepartiSemilavorati()), 1, new Date(2022, 10, 11, 7, 30));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_BRACCIOLO_SEDIA.getNome(), repSedia.getListaRepartiSemilavorati()), 2, new Date(2022, 10, 11, 7, 40));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_SEDUTA_SEDIA.getNome(), repSedia.getListaRepartiSemilavorati()), 1, new Date(2022, 10, 11, 7, 50));
		l.depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_GAMBA_SEDIA.getNome(), repSedia.getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 00));
		
		// controllo correttezza exception quando il reparto di semilavorati è pieno
		assertThrows(RepartoPienoException.class, () -> d.costruisciProdottiFiniti(repSedia, 1, new Date(2022, 10, 14, 7, 40)));
		
		// controllo correttezza exception quando non ho abbastanza semilavorati per costruire un prodotto finito
		assertThrows(RepartoPienoException.class, () -> d.costruisciProdottiFiniti(repSedia, 1, new Date(2022, 10, 14, 7, 40)));
	}
}
