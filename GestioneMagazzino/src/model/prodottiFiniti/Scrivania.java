package model.prodottiFiniti;

public class Scrivania extends ProdottoFinitoImpl{

	// costruttore
	public Scrivania() {
		super("scrivania");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("gamba_scrivania", 4);
		this.getListaComponenti().put("pianale_scrivania", 1);
	}
	
}
