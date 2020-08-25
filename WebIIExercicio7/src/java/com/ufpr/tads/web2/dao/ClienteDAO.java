/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author loko_
 */
public class ClienteDAO {

    private final String buscaClientes = "SELECT * FROM TB_CLIENTE";
    private final String buscaClienteId = "SELECT * FROM TB_CLIENTE WHERE ID_CLIENTE = ?";
    private final String excluiCliente = "DELETE FROM TB_CLIENTE WHERE ID_CLIENTE=?";
    private final String alteraCliente = "UPDATE TB_CLIENTE SET nome_CLIENTE=upper(?),cpf_CLIENTE=?,email_cliente=?,data_cliente=?,rua_cliente=?,nr_cliente=?,cep_cliente=?,cidade_cliente=?,uf_cliente=? WHERE id_CLIENTE=?";
    private final String adicionaCliente = "INSERT INTO TB_CLIENTE(nome_CLIENTE,cpf_CLIENTE,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,cidade_cliente,uf_cliente) VALUES(?,?,?,?,?,?,?,?,?)";

    /* private final String buscaTipo = "SELECT * FROM TB_CLIENTE WHERE NOME = ?";
   
    private final String adicionaTipo = "INSERT INTO TB_CLIENTE(nomeTipo,descricao) VALUES(upper(?),upper(?))";
    
     */

    public List<Cliente> buscaClientes() {
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        List<Cliente> lista = new ArrayList();
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(buscaClientes);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setCpfCliente(rs.getString("cpf_cliente"));
                cli.setNomeCliente(rs.getString("nome_cliente").toUpperCase());
                cli.setEmailCliente(rs.getString("email_cliente"));
                lista.add(cli);
            }
            rs.close();
            stmt.close();
            con.close();
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Cliente buscaClientePorId(int id) throws ClassNotFoundException, SQLException, ParseException {
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        con = new ConnectionFactory().getConnection();
        stmt = con.prepareStatement(buscaClienteId);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Cliente cliente = new Cliente();
        while (rs.next()) {
            cliente.setIdCliente(id);
            cliente.setNomeCliente(rs.getString("nome_cliente"));
            cliente.setEmailCliente(rs.getString("email_cliente"));
            cliente.setCepCliente(rs.getString("cep_cliente"));
            cliente.setCpfCliente(rs.getString("cpf_cliente"));
            cliente.setRuaCliente(rs.getString("rua_cliente"));
            cliente.setNrCliente(rs.getInt("nr_cliente"));
            cliente.setCidadeCliente(rs.getString("cidade_cliente"));
            cliente.setUfCliente(rs.getString("uf_cliente"));
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String result = fmt.format(rs.getDate("data_cliente"));
            Date dat = fmt.parse(result);
            cliente.setDataCliente(dat);
        }
        rs.close();
        stmt.close();
        con.close();
        return cliente;
    }

    public void excluiCliente(int id) throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stmt;
        con = new ConnectionFactory().getConnection();
        stmt = con.prepareStatement(excluiCliente);
        stmt.setInt(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();

    }

    public void alteraCliente(Cliente cli) throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stmt;
        con = new ConnectionFactory().getConnection();
        stmt = con.prepareStatement(alteraCliente);
        stmt.setString(1, cli.getNomeCliente());
        stmt.setString(2, cli.getCpfCliente());
        stmt.setString(3, cli.getEmailCliente());
        stmt.setDate(4,new java.sql.Date(cli.getDataCliente().getTime()));
        stmt.setString(5, cli.getRuaCliente());
        stmt.setInt(6, cli.getNrCliente());
        stmt.setString(7, cli.getCepCliente());
        stmt.setString(8, cli.getCidadeCliente());
        stmt.setString(9, cli.getUfCliente());
        stmt.setInt(10, cli.getIdCliente());
        stmt.executeUpdate();
        stmt.close();
        con.close();
      
    }
        
    

    public void adicionaCliente(Cliente cli) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement stmt;
        con = new ConnectionFactory().getConnection();
        stmt = con.prepareStatement(adicionaCliente);
        stmt.setString(1, cli.getNomeCliente());
        stmt.setString(2, cli.getCpfCliente());
        stmt.setString(3, cli.getEmailCliente());
        stmt.setDate(4,new java.sql.Date(cli.getDataCliente().getTime()));
        stmt.setString(5, cli.getRuaCliente());
        stmt.setInt(6, cli.getNrCliente());
        stmt.setString(7, cli.getCepCliente());
        stmt.setString(8, cli.getCidadeCliente());
        stmt.setString(9, cli.getUfCliente());
        stmt.executeUpdate();
        stmt.close();
        con.close();
        
    }
/*
    public Tipo buscaTipo(Tipo tipo) throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        con = new ConnectionFactory().getConnection();
        stmt = con.prepareStatement(buscaTipo);
        stmt.setString(1, tipo.getNomeTipo());
        rs = stmt.executeQuery();
        while (rs.next()) {
            tipo.setCodigoTipo(rs.getInt("idTipo"));
            tipo.setDescricao(rs.getString("descricao"));
        }
        rs.close();
        stmt.close();
        con.close();
        return tipo;
    }
       
    


    

 */
}
