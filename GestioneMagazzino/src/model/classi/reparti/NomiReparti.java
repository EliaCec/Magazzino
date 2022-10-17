package model.classi.reparti;

public enum NomiReparti {
	// reparti armadio
	REPARTO_ARMADIO("reparto_armadio"),
	REPARTO_ANTA_ARMADIO("reparto_anta_armadio"),
	REPARTO_PANNELLO_GRANDE_ARMADIO("reparto_pannello_grande_aramdio"),
	REPARTO_PANNELLO_PICCOLO_ARMADIO("reparto_pannello_piccolo_armadio"),
	
	// reparti sedia
	REPARTO_SEDIA("reparto_sedia"),
	REPARTO_BRACCIOLO_SEDIA("reparto_bracciolo_sedia"),
	REPARTO_GAMBA_SEDIA("reparto_gamba_sedia"),
	REPARTO_SEDUTA_SEDIA("reparto_seduta_armadio"),
	REPARTO_SCHIENALE_SEDIA("reparto_schienale_armadio"),
	
	// reparti scrivania
	REPARTO_SCRIVANIA("reparto_scrivania"),
	REPARTO_GAMBA_SCRIVANIA("reparto_gamba_scrivania"),
	REPARTO_PIANALE_SCRIVANIA("reparto_pianale_scrivania"),
	
	// reparti mensola
	REPARTO_MENSOLA("reparto_mensola"),
	REPARTO_RIPIANO_MENSOLA("reparto_ripiano_mensola"),
	REPARTO_STAFFA_MENSOLA("reparto_staffa_mensola");
	
	private String nomeReparto;
	private 
	
	NomiReparti(String n) {
		this.nomeReparto = n;
	}
	
	public String getNome() {
		return this.nomeReparto;
	}
	
}
