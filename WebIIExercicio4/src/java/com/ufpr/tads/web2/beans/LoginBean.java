package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class LoginBean implements Serializable {

    private int idUsuario;
    private String loginUsuario;
    private String nomeUsuario;

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String login) {
        this.loginUsuario = login;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nome) {
        this.nomeUsuario = nome;
    }
}
