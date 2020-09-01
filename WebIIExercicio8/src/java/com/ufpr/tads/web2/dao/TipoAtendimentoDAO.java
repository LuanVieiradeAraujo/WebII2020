package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ufpr.tads.web2.beans.TipoAtendimento;

public class TipoAtendimentoDAO {
    Connection conn = null;

    public List<TipoAtendimento> all() throws SQLException {
        List<TipoAtendimento> listaTipoAtendimentos = new ArrayList<>();
        try {
            conn = ConnectionFactory.getConnection();
            ResultSet rs = ConnectionFactory.getResultSet(conn, "SELECT * FROM tb_tipo_atendimento");

            while (rs.next()) {
                TipoAtendimento tipoAtendimento = new TipoAtendimento();
                tipoAtendimento.setIdTipoAtendimento(rs.getInt("id_tipo_atendimento"));
                tipoAtendimento.setNomeTipoAtendimento(rs.getString("nome_tipo_atendimento"));
                listaTipoAtendimentos.add(tipoAtendimento);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();
        }

        return listaTipoAtendimentos;
    }

    public TipoAtendimento buscarTipoAtendimentoPorId(int idAtendimento) throws SQLException {
        conn = null;
        TipoAtendimento tipoAtendimento = new TipoAtendimento();
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement statement = ConnectionFactory.getPreparedStatement(conn,
                    "SELECT * FROM tb_tipo_atendimento WHERE id_tipo_atendimento = ?");
            statement.setInt(1, idAtendimento);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tipoAtendimento.setIdTipoAtendimento(rs.getInt("id_tipo_atendimento"));
                tipoAtendimento.setNomeTipoAtendimento(rs.getString("nome_tipo_atendimento"));
            } else
                tipoAtendimento = null;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.close();

            return tipoAtendimento;
        }
    }
}
