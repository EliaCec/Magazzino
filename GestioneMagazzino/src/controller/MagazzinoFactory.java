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
	public MagazzinoFactory() {
		
	}
	
	// metodo che crea un dirigente
	public static Magazzino creaMagazzino() {
		Dirigente dir = creaDirigente();
		return new Magazzino(dir);
	}
	
	@SuppressWarnings("deprecation")
	private static Dirigente creaDirigente() {
		Dirigente dir = new DirigenteImpl();
		// creazione reparti
		dir.creaReparto(new RepartoArmadio()); 
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
		dir.cambioTurno(nuoviOperaiAttivi, nuoviResponsabiliAttivi, new Date(2022, 01, 01, 7, 0));
		return dir;
	}
	
}
