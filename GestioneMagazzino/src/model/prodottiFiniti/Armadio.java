package model.prodottiFiniti;

public class Armadio extends ProdottoFinitoImpl{

	// costruttore
	public Armadio() {
		super("armadio");
		this.inserisciComponenti();	
	}

	public void inserisciComponenti() {
		this.getComponenti().put("anta_armadio", 2);
		this.getComponenti().put("pannello_piccolo_armadio", 4);
		this.getComponenti().put("pannello_grande_armadio", 1);
	}
	
}
