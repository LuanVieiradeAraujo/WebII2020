package com.ufpr.tads.web2.facade;

import java.sql.SQLException;
import java.util.List;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;

public class TipoAtendimentoFacade {
    public static final TipoAtendimentoDAO atendimentoDao = new TipoAtendimentoDAO();

    public static List<TipoAtendimento> buscarTodosTiposAtendimentos() throws SQLException {
        List<TipoAtendimento> listaTiposAtendimentos = atendimentoDao.all();
        return listaTiposAtendimentos;
    }

    public static TipoAtendimento buscaTipoAtendimentoPorId(int id) throws SQLException {
        TipoAtendimento tpAtendimento = atendimentoDao.buscarTipoAtendimentoPorId(id);
        return tpAtendimento;
    }
}
