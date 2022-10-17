package model.classi.prodottiFiniti;

public class Mensola extends ProdottoFinitoImpl{

	// costruttore
	public Mensola() {
		super("mensola");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("staffa_mensola", 2);
		this.getListaComponenti().put("ripiano_mensola", 1);
	}
	
}
