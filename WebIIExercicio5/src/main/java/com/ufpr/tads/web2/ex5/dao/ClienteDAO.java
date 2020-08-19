package com.ufpr.tads.web2.ex5.dao;

import com.ufpr.tads.web2.ex5.beans.Cliente;
import com.ufpr.tads.web2.ex5.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class ClienteDAO {

    private static final String TABELA = "tb_clientes";

    public static List<Cliente> list() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Cliente> clientes = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT id_cliente, nome_cliente, login_cliente, senha_cliente FROM " + TABELA + ";"
            );
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome_cliente"),
                    rs.getString("login_cliente"),
                    rs.getString("senha_cliente")
                ));
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean getCliente(String idCliente) {     
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_cliente, cpf_cliente, nome_cliente, email_cliente FROM " + TABELA
                + " WHERE id_cliente = ?;"
            );
            stmt.setString(1, idCliente);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente");
                        rs.getString("cpf_cliente");
                        rs.getString("nome_cliente");
                        rs.getString("email_cliente");
                );                
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    }
    
