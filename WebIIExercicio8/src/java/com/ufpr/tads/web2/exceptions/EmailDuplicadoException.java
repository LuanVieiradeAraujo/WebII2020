package com.ufpr.tads.web2.exceptions;

public class EmailDuplicadoException extends AppException {
    public EmailDuplicadoException() {
        super();
    }

    public EmailDuplicadoException(String string) {
        super(string);
    }

    public EmailDuplicadoException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EmailDuplicadoException(Throwable thrwbl) {
        super(thrwbl);
    }
}
