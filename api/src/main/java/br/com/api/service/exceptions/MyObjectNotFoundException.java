package br.com.api.service.exceptions;

public class MyObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public MyObjectNotFoundException(String message) {
        super(message);
    }
	
}
