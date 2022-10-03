package model.prodottiFiniti;

public class Mensola extends ProdottoFinitoImpl{

	// costruttore
	public Mensola() {
		super("mensola");
		this.inserisciComponenti();	
	}

	public void inserisciComponenti() {
		this.getComponenti().put("staffa_mensola", 2);
		this.getComponenti().put("ripiano_mensola", 1);
	}
	
}
