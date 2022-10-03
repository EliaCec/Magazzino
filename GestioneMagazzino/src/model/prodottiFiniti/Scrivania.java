package model.prodottiFiniti;

public class Scrivania extends ProdottoFinitoImpl{

	// costruttore
	public Scrivania() {
		super("scrivania");
		this.inserisciComponenti();	
	}

	public void inserisciComponenti() {
		this.getComponenti().put("gamba_scrivania", 4);
		this.getComponenti().put("pianale_scrivania", 1);
	}
	
}
