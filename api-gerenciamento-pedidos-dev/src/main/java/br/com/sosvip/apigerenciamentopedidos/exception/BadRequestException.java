package br.com.sosvip.apigerenciamentopedidos.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
