package model.classi.exception;

public class RepartoPienoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// costruttore
	public RepartoPienoException(int n, int f) {
		super("il reparto è pieno. La quantità attuale è: " + n + "/" + f);
	}
}
