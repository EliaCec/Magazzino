package model.classi.exception;

public class RepartoPienoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// costruttore
	public RepartoPienoException(int n, int f) {
		super("il reparto è pieno. \nLa quantità attuale è: " + n + "/" + f);
	}
}
