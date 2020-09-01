/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Login;
import com.ufpr.tads.web2.facade.ClienteFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "AlterarClienteServlet", urlPatterns = {"/AlterarClienteServlet"})
public class AlterarClienteServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Login log = (Login) session.getAttribute("login");
        if (log == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            rd.forward(request, response);
        } else {

            try {
                Cliente cli = new Cliente();
                cli.setIdCliente(Integer.parseInt(request.getParameter("id")));
                cli.setNomeCliente(request.getParameter("nome"));
                cli.setCpfCliente(request.getParameter("cpf"));
                cli.setEmailCliente(request.getParameter("email"));

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String str = request.getParameter("data");   // Data como String
                Date data = format.parse(str);

                SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");

                String dataFormatada = null;

                dataFormatada = formatoDesejado.format(data);
                Date dat = formatoDesejado.parse(dataFormatada);

                cli.setDataCliente(dat);
                cli.setRuaCliente(request.getParameter("rua"));
                cli.setNrCliente(Integer.parseInt(request.getParameter("numero")));
                cli.setCepCliente(request.getParameter("cep"));
                cli.setCidadeCliente(request.getParameter("cidade"));
                cli.setUfCliente(request.getParameter("uf"));
                ClienteFacade.alterar(cli);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet");
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema " + e);
                session.setAttribute("page", "portal");
                response.sendRedirect("erro.jsp");
            } catch (ParseException ex) {
                session.setAttribute("msg", "Erro Parsing " + ex);
                session.setAttribute("page", "index.html");
                response.sendRedirect("erro.jsp");
            }

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
