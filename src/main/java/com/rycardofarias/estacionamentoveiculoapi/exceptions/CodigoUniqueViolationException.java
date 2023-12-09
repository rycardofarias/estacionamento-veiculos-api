package com.rycardofarias.estacionamentoveiculoapi.exceptions;

public class CodigoUniqueViolationException extends RuntimeException {

    public CodigoUniqueViolationException(String message) {
        super(message);
    }
}
