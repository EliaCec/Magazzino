package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Dirigente;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.DirigenteImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.reparti.NomiReparti;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

public class TestGenerale {
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
	public void testGenerale() {
		// creazione reparti
		dir.creaReparto(repArmadio);
		dir.creaReparto(repMensola);
		dir.creaReparto(repScrivania);
		dir.creaReparto(repSedia);
		
		// assunzione di quattro operai
		dir.assumiDipendente(d);
		dir.assumiDipendente(f);
		dir.assumiDipendente(t);
		dir.assumiDipendente(a);
		
		// assunzione due responsabili
		dir.assumiDipendente(l);
		dir.assumiDipendente(m);
		
		// attivo operai e responsabili
		List<Operaio> operai = new LinkedList<>();
		operai.add(d);
		operai.add(f);
		operai.add(t);
		operai.add(a);
		List<Responsabile> responsabili = new LinkedList<>();
		responsabili.add(l);
		responsabili.add(m);
		dir.cambioTurno(operai, responsabili, new Date(2022, 10, 11, 7, 30));
		
		// deposito semilavorati per test
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_ANTA_ARMADIO.getNome(), ((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), dir.getReparti())).getListaRepartiSemilavorati()), 8, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO.getNome(), ((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), dir.getReparti())).getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO.getNome(), ((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), dir.getReparti())).getListaRepartiSemilavorati()), 16, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_GAMBA_SCRIVANIA.getNome(), ((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(), dir.getReparti())).getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PIANALE_SCRIVANIA.getNome(), ((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(), dir.getReparti())).getListaRepartiSemilavorati()), 4, new Date(2022, 10, 11, 8, 30));
	    
	    // costruisco prodotti finiti
	    ((Operaio) dir.cercaDipendentePerNome("Daniele_Rossi", dir.getOperaiAttivi())).costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 00));
	    ((Operaio) dir.cercaDipendentePerNome("Daniele_Rossi", dir.getOperaiAttivi())).costruisciProdottiFiniti(repScrivania, 1, new Date(2022, 10, 11, 9, 00));
	    
		// vendita prodotti finiti
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).vendiProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).vendiProdottiFiniti(repScrivania, 1, new Date(2022, 10, 11, 12, 00));
	   
	    
		// test controllo vendite giornaliere
		assertEquals(3, dir.storicoVenditeGiornaliero(new Date(2022, 10, 11)).size());

		// test controllo costruzioni giornaliere
		assertEquals(3, dir.storicoCostruzioniGiornaliero(new Date(2022, 10, 11)).size());
		
		// test controllo semilavorati usati in un giorno
		assertEquals(19, dir.storicoSemilavoratiUsatiGiornaliero(new Date(2022, 10, 11)).size());
		
	}
}
