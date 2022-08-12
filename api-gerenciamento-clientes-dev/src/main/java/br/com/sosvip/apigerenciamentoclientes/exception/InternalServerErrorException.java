package br.com.sosvip.apigerenciamentoclientes.exception;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message) {
        super(message);
    }
}
