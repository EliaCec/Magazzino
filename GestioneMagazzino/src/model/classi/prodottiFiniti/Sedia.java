package model.classi.prodottiFiniti;

public class Sedia extends ProdottoFinitoImpl{

	// costruttore
	public Sedia() {
		super("sedia");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("schienale_sedia", 1);
		this.getListaComponenti().put("bracciolo_sedia", 2);
		this.getListaComponenti().put("seduta_sedia", 1);
		this.getListaComponenti().put("gamba_sedia", 4);
	}
	
}
