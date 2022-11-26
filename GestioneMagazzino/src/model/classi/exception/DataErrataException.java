package model.classi.exception;

public class DataErrataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	// costruttore
	public DataErrataException(String msg) {
		super(msg);
	}
}
