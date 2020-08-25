/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Carolina
 */
public class ClienteFacade {

   
     
     public static List<Cliente> buscaClientes() throws ClassNotFoundException, SQLException {
        
        ClienteDAO cDAO = new ClienteDAO();
        //retorna um usuario ou não conforme a verificação
        List<Cliente> u = cDAO.buscaClientes();
         return u;
    }
     
     public static void inserir(Cliente c) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDao = new ClienteDAO();
        cliDao.adicionaCliente(c);
    }

    public static void alterar(Cliente c) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDao = new ClienteDAO();
        cliDao.alteraCliente(c);
    }

    public static Cliente buscar(int id) throws ClassNotFoundException, SQLException, ParseException{
        ClienteDAO cliDao = new ClienteDAO();
        return cliDao.buscaClientePorId(id);
    }
    
    public static void deletar(int id) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDao = new ClienteDAO();
        cliDao.excluiCliente(id);
    }

    

}
