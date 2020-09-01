
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UsuarioFacade {
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public static String criptografarSenha(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException{
       MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
       byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
         
       StringBuilder hexString = new StringBuilder();
       for (byte b : messageDigest) {
         hexString.append(String.format("%02X", 0xFF & b));
       }
       String senhahex = hexString.toString();
        
       System.out.println(senhahex);
       return senhahex;
    }
    
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
     
     public static Usuario buscarPorId(int id) throws SQLException{
        Usuario usuario = usuarioDAO.findById(id);
        return usuario;
    }
     
    public static Usuario buscarPorId(int id, String cliente) throws SQLException {
        Usuario usuario = usuarioDAO.findById(id, cliente);
        return usuario;
    }
}
