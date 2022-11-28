package model.classi.exception;

public class ProdottiInsufficientiException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	// costruttore
	public ProdottiInsufficientiException(String pf, int i) {
		super("I " + pf + " da vendere sono insufficenti, mancano " + i + " prodotti");
	}
}
