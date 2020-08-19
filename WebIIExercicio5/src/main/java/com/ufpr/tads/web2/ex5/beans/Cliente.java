package com.ufpr.tads.web2.ex5.beans;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

    private int id_cliente;
    private String cpf_cliente;
    private String nome_cliente;
    private String email_cliente;
    private Date data_cliente;
    private String rua_cliente;
    private String nr_cliente;
    private String cep_cliente;
    private String cidade_cliente;
    private String uf_cliente;
    
    public Cliente(int id_cliente, String cpf_cliente, String nome_cliente, String email_cliente) {
       this.id_cliente = id_cliente;
       this.cpf_cliente = cpf_cliente;
       this.nome_cliente = nome_cliente;
       this.email_cliente = email_cliente;
    }

    public int getClienteId(){
        return this.id_cliente;
    }
     
}
