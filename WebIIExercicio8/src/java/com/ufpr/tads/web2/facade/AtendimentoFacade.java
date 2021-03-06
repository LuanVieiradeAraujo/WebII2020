package com.ufpr.tads.web2.facade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.dao.AtendimentoDAO;

public class AtendimentoFacade {
    public static final AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

    public static List<Atendimento> buscarTodosAtendimentos() throws SQLException {
        List<Atendimento> listaAtendimentos = atendimentoDAO.all();
        return listaAtendimentos;
    }

    public static List<Atendimento> buscarTodosAtendimentosAbertos() throws SQLException {
        List<Atendimento> listaAtendimentos = atendimentoDAO.buscarAtendimentosAbertos();
        return listaAtendimentos;
    }

    public static Atendimento buscarAtendimentoPorId(int id) throws SQLException {
        Atendimento atendimento = atendimentoDAO.findById(id);
        return atendimento;
    }

    public static void inserirAtendimento(Atendimento atendimento) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        atendimentoDAO.insert(atendimento);
    }

    public static void removerAtendimento(int idAtendimento) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        atendimentoDAO.delete(idAtendimento);
    }

    public static LocalDateTime converterStrToLocalDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse(data, formatter);
        return date;
    }

    public static List<Atendimento> buscarTodosAtendimentosUser(int id) throws SQLException {
        List<Atendimento> listaAtendimentos = atendimentoDAO.allByUser(id);
        return listaAtendimentos;
    }

    public static void responderAtendimento(int id, String solucao) throws SQLException {
        atendimentoDAO.responder(id, solucao);
    }
}
