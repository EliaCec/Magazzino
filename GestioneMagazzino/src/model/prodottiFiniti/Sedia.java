package model.prodottiFiniti;

public class Sedia extends ProdottoFinitoImpl{

	// costruttore
	public Sedia() {
		super("sedia");
		this.inserisciComponenti();	
	}

	public void inserisciComponenti() {
		this.getComponenti().put("schienale_sedia", 1);
		this.getComponenti().put("bracciolo_sedia", 2);
		this.getComponenti().put("seduta_sedia", 1);
		this.getComponenti().put("gamba_sedia", 4);
	}
	
}
