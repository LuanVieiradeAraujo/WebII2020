/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

import java.io.Serializable;

/**
 *
 * @author Carolina
 */
public class Usuario implements Serializable{
    public Usuario(){}
    
    public Usuario(String n,String l, String s){
        this.login = l;
        this.nome = n;
        this.senha = s;
    }
    
    public Usuario(String n,int id){
        this.id = id;
        this.nome = n;
       
    }
    
    private String nome;
    private String login;
    private String senha;
    private int id;

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
