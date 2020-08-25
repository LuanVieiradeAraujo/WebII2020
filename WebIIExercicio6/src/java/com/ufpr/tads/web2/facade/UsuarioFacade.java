/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Carolina
 */
public class UsuarioFacade {

    public static Usuario login(String user, String s) throws ClassNotFoundException, SQLException {
        
        UsuarioDAO uDAO = new UsuarioDAO();
        //retorna um usuario ou não conforme a verificação
        Usuario u = uDAO.verifica(user, s);
         return u;
    }
   
     public static String cadastrar(Usuario u) throws ClassNotFoundException, SQLException {
        
        UsuarioDAO uDAO = new UsuarioDAO();
        
        String msg = uDAO.cadastrar(u);
         return msg;
    }
     
     public static List<Usuario> buscaUsuarios() throws ClassNotFoundException, SQLException {
        
        UsuarioDAO uDAO = new UsuarioDAO();
        //retorna um usuario ou não conforme a verificação
        List<Usuario> u = uDAO.buscarUsuarios();
         return u;
    }
}
