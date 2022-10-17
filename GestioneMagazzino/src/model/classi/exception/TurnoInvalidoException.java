package model.classi.exception;

public class TurnoInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	// costruttore
	public TurnoInvalidoException(String msg) {
		super(msg);
	}
}
