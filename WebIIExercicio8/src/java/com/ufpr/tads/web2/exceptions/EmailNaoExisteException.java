package com.ufpr.tads.web2.exceptions;

public class EmailNaoExisteException extends AppException {
    public EmailNaoExisteException() {
        super();
    }

    public EmailNaoExisteException(String string) {
        super(string);
    }

    public EmailNaoExisteException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EmailNaoExisteException(Throwable thrwbl) {
        super(thrwbl);
    }
}