/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.facade.UsuarioFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carolina
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session;

        session = request.getSession();
        try {

            String usuario = request.getParameter("login");
            String senha = request.getParameter("senha");
            Usuario u = UsuarioFacade.login(usuario, senha);
            if (u != null) {

                LoginBean uBean = new LoginBean(u.getId(), u.getNome());
                List<Usuario> users = UsuarioFacade.buscaUsuarios();
                
                session.setAttribute("login", uBean);
                request.setAttribute("lista", users);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/portal.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Usuário/Senha inválidos.");
                
                rd.forward(request, response);

            }
        } catch (SQLException | ClassNotFoundException ex) {
            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                
                response.sendRedirect("index.jsp");
        }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
