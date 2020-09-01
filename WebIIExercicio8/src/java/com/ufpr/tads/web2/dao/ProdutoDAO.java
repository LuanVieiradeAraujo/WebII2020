package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ufpr.tads.web2.beans.Produto;

public class ProdutoDAO {
    Connection conn = null;

    public List<Produto> all() throws SQLException {
        List<Produto> listaProdutos = new ArrayList<>();
        try {
            conn = ConnectionFactory.getConnection();
            ResultSet rs = ConnectionFactory.getResultSet(conn, "SELECT * FROM tb_produto");

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();
        }

        return listaProdutos;
    }

    public Produto findById(int idProduto) throws SQLException {
        conn = null;
        Produto produto = new Produto();

        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "SELECT * FROM tb_produto WHERE id_produto=?");
            statement.setInt(1, idProduto);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
            } else
                produto = null;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();

            return produto;
        }
    }

    public void insert(Produto produto) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "INSERT INTO tb_produto (nome_produto) values (?)");
            statement.setString(1, produto.getNomeProduto());
            statement.setString(2, produto.getDescricaoProduto());
            statement.setDouble(3, produto.getPesoProduto());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Erro " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();
        }
    }

    public int alterar(Produto produto) {
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "UPDATE tb_produto SET nome_produto=? WHERE id_produto=?");
            statement.setString(1, produto.getNomeProduto());
            statement.setInt(2, produto.getIdProduto());
            statement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
            //throw new RuntimeException("Erro ao atualizar cliente." + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException("Erro ao fechar conexão.");
                }
            }
        }
    }

    public void remove(int id) {
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "DELETE FROM tb_produto WHERE id_produto=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar produto.");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException("Erro ao fechar conexão.");
                }
            }
        }
    }
    
}
