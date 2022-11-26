package model.classi;

public class GeneratoreID {
	
	private static int nuovoIdDipendente;
	private static int nuovoIdGiacenza;
	
	// costruttore
	public GeneratoreID() {}
	
	// metodi per generare Id in ordine crescente
	public static int setGetIdDipendente() {
		int nuovoId = nuovoIdDipendente;
		nuovoIdDipendente++;
		return nuovoId;
	}
	
	public static void inizializzaGeneratore() {
		nuovoIdDipendente = 1; // lo 0 è riservato al dirigente
		nuovoIdGiacenza = 0;
	}
	
	public static int setGetIdGiacenza() {
		int nuovoId = nuovoIdGiacenza;
		nuovoIdGiacenza++;
		return nuovoId;
	}
	
	public static void decrementaIdGiacenza() {
		nuovoIdGiacenza--;
	}
	
	public static int getIdDirigente() {
		return 0;
	}
	
}