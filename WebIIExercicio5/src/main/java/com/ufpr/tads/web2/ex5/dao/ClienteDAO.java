package com.ufpr.tads.web2.ex5.dao;

import com.ufpr.tads.web2.ex5.beans.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ufpr.tads.web2.ex5.beans.Usuario;

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

    public static boolean verifyIfExist(String login) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_cliente FROM " + TABELA + " WHERE login_cliente = ?;"
            );
            stmt.setString(1, login.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
