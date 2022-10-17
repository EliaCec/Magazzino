package model.classi.exception;

public class ProdottiInsufficientiException extends RuntimeException{

	// costruttore
	public ProdottiInsufficientiException(String pf, int i) {
		super("I "+pf+" da vendere sono indufficineti, mancano "+i+"prodotti");
	}
}
