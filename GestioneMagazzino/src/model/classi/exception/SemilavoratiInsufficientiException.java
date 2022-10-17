package model.classi.exception;

import java.util.HashMap;

import model.Semilavorato;

public class SemilavoratiInsufficientiException extends RuntimeException {

	public SemilavoratiInsufficientiException(HashMap<String, Integer> semi) {
		super("Mancano i seguenti semilavorati per la costruzione: " + semi);
	}
}
