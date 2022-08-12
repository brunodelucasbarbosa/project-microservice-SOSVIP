package br.com.sosvip.apigerenciamentopedidos.exception;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message) {
        super(message);
    }
}
