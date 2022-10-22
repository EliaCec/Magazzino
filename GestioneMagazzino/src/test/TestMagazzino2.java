package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import model.Magazzino;
import model.Operaio;
import model.RepartoProdottiFiniti;
import model.Responsabile;
import model.classi.MagazzinoImpl;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;
import model.classi.exception.TurnoInvalidoException;
import model.classi.reparti.RepartoArmadio;
import model.classi.reparti.RepartoMensola;
import model.classi.reparti.RepartoScrivania;
import model.classi.reparti.RepartoSedia;

class TestMagazzino2 {
	
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
	
	// metodo che testa la creazione reparti
	@Test
	void testCreazioneReparti() {
		assertEquals(0, mag.getNumeroReparti()); // verifica assenza reparti
		// creazione di quattro reparti
		mag.creaReparto(repArmadio);
		mag.creaReparto(repMensola);
		mag.creaReparto(repScrivania);
		mag.creaReparto(repSedia);
		assertEquals(4, mag.getNumeroReparti()); // verifica numero corretto di reparti
	}
	
	// metodo che testa l'assunzione dipendenti (sia operai che responsabili)
	@Test
	void testAssunzioneDipendenti() {
		assertEquals(0, mag.getOperaiAssunti().size()); // verifica assenza di operai
		assertEquals(0, mag.getResponsabiliAssunti().size()); // verifica assenza di operai
		// assunzione di quattro operai
		mag.assumiDipendente(d);
		mag.assumiDipendente(f);
		mag.assumiDipendente(t);
		mag.assumiDipendente(a);
		assertEquals(4, mag.getOperaiAssunti().size()); // verifica numero corretto di operai
		// assunzione di due responsabili
		mag.assumiDipendente(l);
		mag.assumiDipendente(m);
		assertEquals(2, mag.getResponsabiliAssunti().size()); // verifica numero corretto di responsabili
	}
	
	// metodo che testa i cambi turni dei dipendenti
	@SuppressWarnings("deprecation")
	@Test
	void testInizioTurno() {
		assertEquals(0, mag.getOperaiAttivi().size()); // verifica assenza di operai in turno
		assertEquals(0, mag.getResponsabiliAttivi().size()); // verifica assenza di responsabili in turno
		// creazione lista di operai e responsabili che iniziano a lavorare
		List<Operaio> operai = new LinkedList<>();
		operai.add(a);
		operai.add(d);
		List<Responsabile> responsabili = new LinkedList<>();
		responsabili.add(l);
		// cambio turno corretto
		mag.cambioTurno(operai, responsabili, new Date(2022, 1, 2, 7, 30));
		assertEquals(2, mag.getOperaiAttivi().size());
		assertEquals(1, mag.getResponsabiliAttivi().size());
		// cambio turno errato (data nuovo turno più vecchia)
		assertThrows(TurnoInvalidoException.class, () -> mag.cambioTurno(operai, responsabili, new Date(1900, 1, 2, 7, 30)));
	}
	
	// metodo che testa la correttezza degli storici giornalieri
	@Test
	void testStoriciGiornalieri() {
		
	}

}
