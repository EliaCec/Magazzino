package model.classi.exception;

public class RepartoPienoException extends RuntimeException {

	public RepartoPienoException(int n, int f) {
		super("il reparto � pieno. La quantit� attuale �" + n + "/" + f);
	}
}
