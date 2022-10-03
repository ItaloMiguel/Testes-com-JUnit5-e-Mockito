package br.com.api.service.exceptions;

public class MyDataIntegratyViolationException extends RuntimeException {

    public MyDataIntegratyViolationException(String message) {
        super(message);
    }
}
