package com.desafio.m2.exception;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message) {
        super(message);
    }
}
