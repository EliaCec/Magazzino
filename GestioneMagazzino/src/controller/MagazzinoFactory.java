package controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

public class MagazzinoFactory {
	
	// costruttore
	public MagazzinoFactory() {}
	
	// metodo che crea un dirigente
	public static Magazzino creaMagazzino() {
		Dirigente dir = creaDirigente();
		return new Magazzino(dir);
	}
	
	@SuppressWarnings("deprecation")
	private static Dirigente creaDirigente() {
		Dirigente dir = new DirigenteImpl();
		// creazione reparti
		RepartoProdottiFiniti repArmadio = new RepartoArmadio(); // DOPO DA TOGLIERE
		dir.creaReparto(repArmadio); // DOPO DA METTERE new RepartoArmadio()
		dir.creaReparto(new RepartoSedia());
		dir.creaReparto(new RepartoScrivania());
		dir.creaReparto(new RepartoMensola());
		// assunzione dipendenti
		dir.assumiDipendente(new OperaioImpl("Marco_Rossi"));
		dir.assumiDipendente(new OperaioImpl("Daniele_Verdi"));
		dir.assumiDipendente(new OperaioImpl("Gianluca_Bianchi"));
		dir.assumiDipendente(new OperaioImpl("Paolo_Gialli"));
		dir.assumiDipendente(new ResponsabileImpl("Gianfranco_Blu"));
		dir.assumiDipendente(new ResponsabileImpl("Simone_Rossi"));
		// inizio primo turno
		List<Operaio> nuoviOperaiAttivi = new LinkedList<>();
		List<Responsabile> nuoviResponsabiliAttivi = new LinkedList<>();
		nuoviOperaiAttivi.add((Operaio)dir.cercaDipendentePerNome("Gianluca_Bianchi", dir.getOperaiAssunti()));
		nuoviOperaiAttivi.add((Operaio)dir.cercaDipendentePerNome("Marco_Rossi", dir.getOperaiAssunti()));
		nuoviResponsabiliAttivi.add((Responsabile)dir.cercaDipendentePerNome("Gianfranco_Blu", dir.getResponsabiliAssunti()));
		nuoviResponsabiliAttivi.add((Responsabile)dir.cercaDipendentePerNome("Simone_Rossi", dir.getResponsabiliAssunti()));
		dir.cambioTurno(nuoviOperaiAttivi, nuoviResponsabiliAttivi, new Date(2022, 01, 01, 7, 30));
		
		//TUTTO DA TOGLIERE A FINE TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// deposito semilavorati per test
	    ((Responsabile) dir.cercaDipendentePerNome("Gianfranco_Blu", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_ANTA_ARMADIO.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 12, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Gianfranco_Blu", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_GRANDE_ARMADIO.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 6, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Gianfranco_Blu", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PANNELLO_PICCOLO_ARMADIO.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_ARMADIO.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 20, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Gianfranco_Blu", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_GAMBA_SCRIVANIA.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 8, new Date(2022, 10, 11, 8, 30));
	    ((Responsabile) dir.cercaDipendentePerNome("Gianfranco_Blu", dir.getResponsabiliAttivi()))
	    				   .depositaSemilavorati(dir.cercaRepartoPerNome(NomiReparti.REPARTO_PIANALE_SCRIVANIA.getNome(),
	    						   				((RepartoProdottiFiniti) dir.cercaRepartoPerNome(NomiReparti.REPARTO_SCRIVANIA.getNome(),
	    						   				dir.getReparti())).getListaRepartiSemilavorati()), 9, new Date(2022, 10, 11, 8, 30));
	    
	    // costruisco prodotti finiti
	    ((Operaio) dir.cercaDipendentePerNome("Gianluca_Bianchi", dir.getOperaiAttivi())).costruisciProdottiFiniti(repArmadio, 2, new Date(2022, 10, 11, 9, 00));
	  // FINO A QUI !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
		return dir;
	}
	
}
