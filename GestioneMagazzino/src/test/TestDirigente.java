package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import model.Dirigente;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.DirigenteImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.exception.DataErrataException;
import model.classi.reparti.NomiReparti;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

class TestDirigente {
	
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
	
	// metodo che testa la creazione reparti
	@Test
	void testCreazioneReparti() {
		assertEquals(0, dir.getNumeroReparti()); // verifica assenza reparti
		assertEquals(new LinkedList<RepartoProdottiFiniti>(), dir.getReparti());
		// creazione di quattro reparti
		dir.creaReparto(repArmadio);
		dir.creaReparto(repMensola);
		dir.creaReparto(repScrivania);
		dir.creaReparto(repSedia);
		LinkedList<RepartoProdottiFiniti> reparti = new LinkedList<>();
		reparti.add(repArmadio);
		reparti.add(repMensola);
		reparti.add(repScrivania);
		reparti.add(repSedia);
		assertEquals(4, dir.getNumeroReparti()); // verifica numero corretto di reparti
		assertEquals(reparti, dir.getReparti()); // verifica presenza dei quattro reparti
	}
	
	// metodo che testa l'assunzione dipendenti (sia operai che responsabili)
	@Test
	void testAssunzioneDipendenti() {
		assertEquals(0, dir.getOperaiAssunti().size()); // verifica assenza di operai
		assertEquals(0, dir.getResponsabiliAssunti().size()); // verifica assenza di operai
		// assunzione di quattro operai
		dir.assumiDipendente(d);
		dir.assumiDipendente(f);
		dir.assumiDipendente(t);
		dir.assumiDipendente(a);
		assertEquals(4, dir.getOperaiAssunti().size()); // verifica numero corretto di operai
		// assunzione di due responsabili
		dir.assumiDipendente(l);
		dir.assumiDipendente(m);
		assertEquals(2, dir.getResponsabiliAssunti().size()); // verifica numero corretto di responsabili
	}
	
	// metodo che testa i cambi turni dei dipendenti
	@SuppressWarnings("deprecation")
	@Test
	void testInizioTurno() {
		assertEquals(0, dir.getOperaiAttivi().size()); // verifica assenza di operai in turno
		assertEquals(0, dir.getResponsabiliAttivi().size()); // verifica assenza di responsabili in turno
		// creazione lista di operai e responsabili che iniziano a lavorare
		List<Operaio> operai = new LinkedList<>();
		operai.add(a);
		operai.add(d);
		List<Responsabile> responsabili = new LinkedList<>();
		responsabili.add(l);
		// cambio turno corretto
		dir.cambioTurno(operai, responsabili, new Date(2022, 1, 2, 7, 30));
		assertEquals(2, dir.getOperaiAttivi().size());
		assertEquals(1, dir.getResponsabiliAttivi().size());
		// cambio turno errato (data nuovo turno più vecchia)
		assertThrows(DataErrataException.class, () -> dir.cambioTurno(operai, responsabili, new Date(1900, 1, 2, 7, 30)));
	}
	
	// metodo che testa la correttezza della ricerca dei dipendenti assunti
	@Test
	void testCercaDipendentiAssuntiPerNome() {
		dir.assumiDipendente(a);
		assertEquals(a, dir.cercaDipendentePerNome("Alberto_Antonelli", dir.getOperaiAssunti()));
		assertThrows(NoSuchElementException.class, () -> dir.cercaDipendentePerNome("Operaio_inventato", dir.getResponsabiliAssunti()));
	}
	
	// metodo che testa la correttezza della ricerca dei dipendenti assunti
	@Test
	void testCercaRepartiPerNome() {
		dir.creaReparto(repArmadio);
		assertEquals(repArmadio, dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(), dir.getReparti()));
		assertThrows(NoSuchElementException.class, () -> dir.cercaRepartoPerNome("Reparto_inventato", dir.getReparti()));
	}
	
	//metodo che testa la correttezza degli storici giornalieri
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
		List<Responsabile> responsabili = new LinkedList<>();
		operai.add(d);
		responsabili.add(l);
		dir.cambioTurno(operai, responsabili, new Date(2022, 10, 11, 7, 30));
		
		// deposito semilavorati per test
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_ANTA_ARMADIO.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 12, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 6, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 20, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_GAMBA_SCRIVANIA.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 8, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PIANALE_SCRIVANIA.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 9, new Date(2022, 10, 11, 8, 30));
	    
	    // costruisco prodotti finiti
	    ((Operaio) dir.cercaDipendentePerNome("Daniele_Rossi", dir.getOperaiAttivi())).costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 00));
	    ((Operaio) dir.cercaDipendentePerNome("Daniele_Rossi", dir.getOperaiAttivi())).costruisciProdottiFiniti(repArmadio, 1, new Date(2022, 10, 11, 9, 00));
	    ((Operaio) dir.cercaDipendentePerNome("Daniele_Rossi", dir.getOperaiAttivi())).costruisciProdottiFiniti(repScrivania, 2, new Date(2022, 8, 11, 9, 30));
	    
		// vendita prodotti finiti
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).vendiProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).vendiProdottiFiniti(repScrivania, 1, new Date(2022, 10, 11, 12, 00));
	    ((Responsabile) dir.cercaDipendentePerNome("Luca_Amadori", dir.getResponsabiliAttivi())).vendiProdottiFiniti(repScrivania, 1, new Date(2022, 8, 11, 12, 00));
	   
	    
		// test controllo vendite giornaliere
		assertEquals(3, dir.storicoVenditeGiornaliero(new Date(2022, 10, 11)).size());
		assertEquals(1, dir.storicoVenditeGiornaliero(new Date(2022, 8, 11)).size());

		// test controllo costruzioni giornaliere
		assertEquals(3, dir.storicoCostruzioniGiornaliero(new Date(2022, 10, 11)).size());
		assertEquals(2, dir.storicoCostruzioniGiornaliero(new Date(2022, 8, 11)).size());
		
		// test controllo semilavorati usati in un giorno
		assertEquals(21, dir.storicoSemilavoratiUsatiGiornaliero(new Date(2022, 10, 11)).size());
		assertEquals(10, dir.storicoSemilavoratiUsatiGiornaliero(new Date(2022, 8, 11)).size());
		
	}

}
