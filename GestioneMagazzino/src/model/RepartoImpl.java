package model;

import java.util.LinkedList;
import java.util.List;
import model.reparti.NomiReparti;
import model.semilavorati.AntaArmadio;
import model.semilavorati.BraccioloSedia;
import model.semilavorati.GambaSedia;
import model.semilavorati.PannelloGrandeArmadio;
import model.semilavorati.PannelloPiccoloArmadio;
import model.semilavorati.PianaleScrivania;
import model.semilavorati.RipianoMensola;
import model.semilavorati.SchienaleSedia;
import model.semilavorati.SedutaSedia;
import model.semilavorati.StaffaMensola;

public class RepartoImpl implements Reparto {
	private static int NUOVO_NUMERO_REPARTO = 0; // codice univoco che verrà assegnato all'eventuale nuovo reparto che (e se) verrà creato
	private final int numeroReparto;			 // codice univoco del reparto
	private final NomiReparti nome;  			 // nome del reparto
	private List<Giacenza> scorte; 				 // scorte presenti nel reparto
	private final int capacita;					 // capacità del magazzino
	
	// costruttore
	public RepartoImpl(int c, NomiReparti name) {
		this.numeroReparto 	  = RepartoImpl.NUOVO_NUMERO_REPARTO;
		this.nome 	  		  = name;
		this.scorte 		  = new LinkedList<>();
		this.capacita 		  = c;
		RepartoImpl.NUOVO_NUMERO_REPARTO++;
	}

	public int getId() {
		return this.numeroReparto;
	}

	public String getNome() {
		return this.nome.getNome();
	}

	public int getQuantita() {
		return this.scorte.size();
	}

	public int getCapacita() {
		return this.capacita;
	}

	public boolean isPresente() {
		return this.scorte.size() > 0;
	}

	public boolean isPieno() {
		return this.scorte.size() == this.capacita;
	}

	public boolean depositaScorte() {
		// il deposito avviene solamente se il reparto non è pieno
		Giacenza daDepositare = creaGiacenza();
		if (!this.isPieno()) {
			this.scorte.add(daDepositare);
			return true;
		}
		return false;
	}

	private Giacenza creaGiacenza() {
		switch (this.getNome()) {
			case "reparto_staffa_mensola":
				return new StaffaMensola();
			case "reparto_seduta_sedia":
				return new SedutaSedia();
			case "reparto_schienale_sedia":
				return new SchienaleSedia();
			case "reparto_ripiano_mensola":
				return new RipianoMensola();
			case "reparto_pianale_scrivania":
				return new PianaleScrivania();
			case "reparto_pannello_piccolo_armadio":
				return new PannelloPiccoloArmadio();
			case "reparto_pannello_grande_armadio":
				return new PannelloGrandeArmadio();
			case "reparto_gamba_sedia":
				return new GambaSedia();
			case "reparto_bracciolo_sedia":
				return new BraccioloSedia();
			case "reparto_anta_armadio":
				return new AntaArmadio();
			default:
				return null; // !!!!lancia eccezzioneeeee!!!!!
		}
	}

	public Giacenza prelevaScorte() {
		// il prelievo avviene solamente se nel reparto è presente la scorta
		if (this.isPresente())
			return this.scorte.remove(0);
		return null; //!!!!!!!!aggiungere lancio eccezzione!!!!!!!!
	}
	
	public Giacenza getGiacenzaReparto() {
		return this.scorte.get(0);
	}
	
}
