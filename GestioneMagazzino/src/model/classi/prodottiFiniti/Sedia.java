package model.classi.prodottiFiniti;

public class Sedia extends ProdottoFinitoImpl{
	private final static int SCHIENALI_NECESSARI = 1;
	private final static int BRACCIOLI_NECESSARI = 2;
	private final static int SEDUTE_NECESSARIE = 1;
	private final static int GAMBE_NECESSARIE = 4;
	// costruttore
	public Sedia() {
		super("sedia");
		this.inserisciComponenti();	
	}

	protected void inserisciComponenti() {
		this.getListaComponenti().put("schienale_sedia", SCHIENALI_NECESSARI);
		this.getListaComponenti().put("bracciolo_sedia", BRACCIOLI_NECESSARI);
		this.getListaComponenti().put("seduta_sedia", SEDUTE_NECESSARIE);
		this.getListaComponenti().put("gamba_sedia", GAMBE_NECESSARIE);
	}
	
}
