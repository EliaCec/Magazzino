package model.classi.prodottiFiniti;

public class Scrivania extends ProdottoFinitoImpl{
	
	private final static int GAMBE_NECESSARIE = 4;
	private final static int PIANALI_NECESSARI = 1;
	
	// costruttore
	public Scrivania() {
		super("scrivania");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("gamba_scrivania", GAMBE_NECESSARIE);
		this.getListaComponenti().put("pianale_scrivania", PIANALI_NECESSARI);
	}
	
}
