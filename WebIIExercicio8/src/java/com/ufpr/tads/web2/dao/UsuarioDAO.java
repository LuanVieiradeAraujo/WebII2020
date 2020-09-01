package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario verifica(String login, String senha) {
        Connection con = null;
        PreparedStatement stmLogin = null;
        try {
            //tenta uma nova conexão com o banco de dados
            con = new ConnectionFactory().getConnection();
            con.setAutoCommit(false);
            //faz o select pra ver se o login existe
            stmLogin = con.prepareStatement("SELECT * FROM tb_usuario WHERE login=? AND senha=?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmLogin.setString(1, login);
            stmLogin.setString(2, senha);
            ResultSet rs = stmLogin.executeQuery();
            if (rs.next()) {

                Usuario u = new Usuario(rs.getString(4), rs.getInt(1));

                return u;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String cadastrar(Usuario u) {
        String msg;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement("INSERT INTO TB_USUARIO(login,senha,nome) VALUES(?,?,?)");
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            msg = "Cadastro Efetuado!";
            return msg;
        } catch (SQLException e) {
            msg = e.toString();
        }

        return null;
    }

    public List<Usuario> buscarUsuarios() {
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        List<Usuario> lista = new ArrayList();
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement("SELECT * FROM TB_USUARIO");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                lista.add(u);
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
    
    public Usuario findById(int idUser) throws SQLException {
        Connection conn = null;
        Usuario user = new Usuario();
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "SELECT * FROM tb_usuario WHERE id_usuario = ? ");
            statement.setInt(1, idUser);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id_usuario"));
                user.setLogin(rs.getString("login"));
                user.setNome(rs.getString("nome"));
            } else
                user = null;
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());

        } finally {
            if (conn != null)
                conn.close();

            return user;
        }
    }
    
    public Usuario findById(int id, String tipoUsuario) throws SQLException {
        Connection conn = null;
        Usuario user = new Usuario();
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "SELECT * FROM tb_usuario WHERE id_usuario = ? AND tipo_usuario = ?");
            statement.setInt(1, id);
            statement.setString(2, tipoUsuario);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id_usuario"));
                user.setLogin(rs.getString("login"));
                user.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();

            return user;
        }
    }
    
    public Usuario findByLogin(String login, String passwd) throws SQLException {
        Connection conn = null;
        Usuario user = new Usuario();
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "SELECT * FROM tb_usuario WHERE email=? AND senha=?");
            statement.setString(1, login);
            statement.setString(2, passwd);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id_usuario"));
                user.setLogin(rs.getString("login"));
                user.setNome(rs.getString("nome"));
            } else
                user = null;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();
            
            return user;
        }
    }
}
