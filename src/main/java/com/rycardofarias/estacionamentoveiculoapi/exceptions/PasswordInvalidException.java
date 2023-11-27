package com.rycardofarias.estacionamentoveiculoapi.exceptions;

public class PasswordInvalidException extends RuntimeException{

    public PasswordInvalidException(String message) {
        super(message);
    }
}
