package com.trabajouy.exceptions;

@SuppressWarnings("serial")
public class EmpresasNoExistenException extends Exception{
	public EmpresasNoExistenException(String string) {
		super(string);
	}
}