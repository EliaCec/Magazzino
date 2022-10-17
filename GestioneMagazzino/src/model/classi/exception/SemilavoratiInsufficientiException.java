package model.classi.exception;

import java.util.HashMap;

public class SemilavoratiInsufficientiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// costruttore
	public SemilavoratiInsufficientiException(HashMap<String, Integer> semi) {
		super("Mancano i seguenti semilavorati per la costruzione: " + semi);
	}
}
