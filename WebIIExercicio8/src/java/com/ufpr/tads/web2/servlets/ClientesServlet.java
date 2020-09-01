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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

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
        RequestDispatcher rd = null;
        if (log == null) {
             rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            rd.forward(request, response);
        } else {

            String action = request.getParameter("action");
            if (action == null) {
                try {
                    List<Cliente> clientes = ClienteFacade.buscaClientes();
                    request.setAttribute("lista", clientes);
                    rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
                    rd.forward(request, response);
                } catch (ClassNotFoundException ex) {
                    session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                    session.setAttribute("page", "ClientesServlet");
                    response.sendRedirect("erro.jsp");
                } catch (SQLException ex) {
                    session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                    session.setAttribute("page", "ClientesServlet");
                    response.sendRedirect("erro.jsp");
                }
                
            } else {
                switch (action) {
                    case "list":
                        try {
                            List<Cliente> clientes = ClienteFacade.buscaClientes();
                            request.setAttribute("lista", clientes);
                             rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
                            rd.forward(request, response);
                        } catch (ClassNotFoundException ex) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet");
                            response.sendRedirect("erro.jsp");
                        } catch (SQLException ex) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet");
                            response.sendRedirect("erro.jsp");
                        }
                        break;
                    case "show":

                        try {
                            Cliente cli;
                            int id;
                            
                            id = Integer.parseInt(request.getParameter("id"));
                            cli = new Cliente();
                            cli = ClienteFacade.buscar(id);
                            rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
                            request.setAttribute("cli", cli);
                            rd.forward(request, response);
                        } catch (SQLException ex) {
                            session.setAttribute("msg", "Erro ao listar tipos de atividade no banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet");
                            response.sendRedirect("erro.jsp");
                        } catch (ClassNotFoundException ex) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet");
                            response.sendRedirect("erro.jsp");
                        } catch (ParseException ex) {

                        }

                        break;

                    case "formUpdate":

                        try {
                            Cliente cli;
                            int id;
                            
                            id = Integer.parseInt(request.getParameter("id"));
                            cli = new Cliente();
                            cli = ClienteFacade.buscar(id);
                            rd = getServletContext().getRequestDispatcher("/clientesAlterar.jsp");
                            request.setAttribute("cli", cli);
                            rd.forward(request, response);
                        } catch (SQLException ex) {
                            session.setAttribute("msg", "Erro ao listar tipos de atividade no banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet?action=");
                            response.sendRedirect("erro.jsp");
                        } catch (ClassNotFoundException ex) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet?action=");
                            response.sendRedirect("erro.jsp");
                        } catch (ParseException ex) {

                        }
                        break;

                    case "remove":

                        try {
                            int id;
                            
                            id = Integer.parseInt(request.getParameter("id"));
                            ClienteFacade.deletar(id);
                            rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=");
                            rd.forward(request, response);
                        } catch (SQLException ex) {
                            session.setAttribute("msg", "Erro no banco de dados. Contate o administrador do sistema: " + ex);
                            session.setAttribute("page", "ClientesServlet?action=");
                            response.sendRedirect("erro.jsp");
                        } catch (ClassNotFoundException ex) {
                            session.setAttribute("msg", "Erro de classe. Contate o administrador do sistema: " + ex);
                            session.setAttribute("page", "ClientesServlet?action=");
                            response.sendRedirect("erro.jsp");
                        }

                        break;

                    case "update":
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
                             rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=");
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
                        break;

                    case "formNew":
                         rd = getServletContext().getRequestDispatcher("/clientesNovo.jsp");
                        rd.forward(request, response);
                        break;
                    case "new":
                        try {
                            Cliente cli = new Cliente();
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
                            ClienteFacade.inserir(cli);
                            rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=");
                            rd.forward(request, response);
                        } catch (SQLException | ClassNotFoundException e) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema " + e);
                            session.setAttribute("page", "portal");
                            response.sendRedirect("erro.jsp");
                        } catch (ParseException ex) {
                            session.setAttribute("msg", "Erro Parsing " + ex);
                            session.setAttribute("page", "index.jsp");
                            response.sendRedirect("erro.jsp");
                        }
                        break;
                    default:
                        try {
                            List<Cliente> clientes = ClienteFacade.buscaClientes();
                            request.setAttribute("lista", clientes);
                            rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
                            rd.forward(request, response);
                        } catch (ClassNotFoundException ex) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet");
                            response.sendRedirect("erro.jsp");
                        } catch (SQLException ex) {
                            session.setAttribute("msg", "Erro ao se conectar com o banco de dados. Contate o administrador do sistema");
                            session.setAttribute("page", "ClientesServlet");
                            response.sendRedirect("erro.jsp");
                        }
                        break;
                }
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
