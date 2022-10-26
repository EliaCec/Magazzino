package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Magazzino;
import model.RepartoProdottiFiniti;
import model.classi.MagazzinoImpl;
import model.classi.reparti.RepartoSedia;

class TestReparti {
	
	Magazzino mag = new MagazzinoImpl();
	RepartoProdottiFiniti repSedia = new RepartoSedia();
	
	// metodo che testa il deposito delle scorte in un reparto
	@Test
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
	
	// metodo che testa il prelievo delle scorte
	@Test
	public void testPrelieviReparto() {
		// deposito di 5 scorte
		for (int i = 0; i < 60; i++)
			repSedia.depositaScorte();
		
		// PRELIEVO di 4 le scorte eccetto una
		for (int i = 0; i < 45; i++)
			repSedia.prelevaScorte();
		
		assertEquals(15, repSedia.getQuantita()); // verifica quantità corretta di scorte
		assertFalse(repSedia.isPieno()); // verifica reparto non pieno

	}

}
