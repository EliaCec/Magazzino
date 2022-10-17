package model.classi.exception;

public class RepartoPienoException extends RuntimeException {

	public RepartoPienoException(int n, int f) {
		super("il reparto è pieno. La quantità attuale è" + n + "/" + f);
	}
}
