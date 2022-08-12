package br.com.sosvip.apigerenciamentopedidos.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {

        super(message);
    }
}
