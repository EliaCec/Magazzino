package model.classi.prodottiFiniti;

public class Mensola extends ProdottoFinitoImpl{
	
	private final static int STAFFE_NECESSARIE = 2;
	private final static int RIPIANI_NECESSARI = 1;
	
	// costruttore
	public Mensola() {
		super("mensola");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("staffa_mensola", STAFFE_NECESSARIE);
		this.getListaComponenti().put("ripiano_mensola", RIPIANI_NECESSARI);
	}
	
}
