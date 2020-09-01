package com.ufpr.tads.web2.exceptions;

public class UsuarioSenhaInvalidosException extends AppException {
    public UsuarioSenhaInvalidosException() {
        super();
    }

    public UsuarioSenhaInvalidosException(String string) {
        super(string);
    }

    public UsuarioSenhaInvalidosException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UsuarioSenhaInvalidosException(Throwable thrwbl) {
        super(thrwbl);
    }
}