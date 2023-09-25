package com.trabajouy.exceptions;

@SuppressWarnings("serial")
public class ExisteUnUsuarioYaRegistradoException extends Exception {
	public ExisteUnUsuarioYaRegistradoException(String string) {
		super(string);
	}
}
