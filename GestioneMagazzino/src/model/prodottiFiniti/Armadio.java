package model.prodottiFiniti;

public class Armadio extends ProdottoFinitoImpl{

	// costruttore
	public Armadio() {
		super("armadio");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("anta_armadio", 2);
		this.getListaComponenti().put("pannello_piccolo_armadio", 4);
		this.getListaComponenti().put("pannello_grande_armadio", 1);
	}
	
}
