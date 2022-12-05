package model.classi.prodottiFiniti;

public class Armadio extends ProdottoFinitoImpl{
	
	private final static int ANTE_NECESSARIE = 2;
	private final static int PANNELLI_PICCOLI_NECESSARI = 4;
	private final static int PANNELLI_GRANDI_NECESSARI = 1;

	// costruttore
	public Armadio() {
		super("armadio");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("anta_armadio", ANTE_NECESSARIE);
		this.getListaComponenti().put("pannello_piccolo_armadio", PANNELLI_PICCOLI_NECESSARI);
		this.getListaComponenti().put("pannello_grande_armadio", PANNELLI_GRANDI_NECESSARI);
	}
	
}
