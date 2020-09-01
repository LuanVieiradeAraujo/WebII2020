package com.ufpr.tads.web2.exceptions;

public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String string) {
        super(string);
    }

    public AppException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public AppException(Throwable thrwbl) {
        super(thrwbl);
    }
}
