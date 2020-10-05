package com.merqueo.co.core.domain.exceptions;

public class ConnectivityException extends RuntimeException {
    private static final String NO_INTERNET_CONNECTION_MESSAGE = "No hay conexión a internet. Intenta más tarde.";
    public ConnectivityException() {
        super(NO_INTERNET_CONNECTION_MESSAGE);
    }
}
