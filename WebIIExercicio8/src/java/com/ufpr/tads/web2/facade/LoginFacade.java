package com.ufpr.tads.web2.facade;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.beans.Usuario;

public class LoginFacade {
    private static final UsuarioDAO userDAO = new UsuarioDAO();
    
    public static Usuario buscarUsuario(String login, String passwd) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        String senha = UsuarioFacade.criptografarSenha(passwd);
        Usuario user = userDAO.findByLogin(login, senha);
        return user;
    }
    
    public static Usuario buscarUsuarioPorId(int idUser) throws SQLException{
        Usuario user = userDAO.findById(idUser);
        return user;
    }
}