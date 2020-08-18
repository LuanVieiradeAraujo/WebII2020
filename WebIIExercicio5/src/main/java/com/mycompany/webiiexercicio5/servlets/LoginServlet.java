package com.mycompany.webiiexercicio5.servlets;

import com.mycompany.webiiexercicio5.beans.LoginBean;
import com.mycompany.webiiexercicio5.beans.Usuario;
import com.mycompany.webiiexercicio5.dao.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        request.setCharacterEncoding("UTF-8");
        
        boolean logado = false, valid = false;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        HttpSession session = request.getSession(false);
        
        if (login == null && senha == null && request.getSession(false) != null) {
           response.sendRedirect("portal.jsp");
           return;
        }
        
        Usuario usuario;
        if (login != null && senha != null) {
            usuario = UsuarioDAO.validate(login, senha);
            if (usuario != null) {
                LoginBean bean = new LoginBean();
                bean.setIdUsuario(usuario.getId());
                bean.setLoginUsuario(usuario.getLogin());
                bean.setNomeUsuario(usuario.getNome());
                request.getSession().setAttribute("usuarioLogado", bean);
                response.sendRedirect("portal.jsp");
                return;
            }
        }
        
        request.setAttribute("msg", "Dados de login inválidos.");
        request.setAttribute("page", request.getContextPath() + "/");
        request.getRequestDispatcher("erro.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
