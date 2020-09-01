package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import com.ufpr.tads.web2.beans.Login;
import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.facade.TipoAtendimentoFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.LoginFacade;

@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/AtendimentoServlet"})
public class AtendimentoServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();

            RequestDispatcher rd;
            Login loginBean = (Login) session.getAttribute("loginBean");
            if (loginBean == null) {
                request.setAttribute("msg", "Acesso Inválido.");
                rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } else {
                String action = request.getParameter("action");
                List<Atendimento> listaAtendimentos;
                Atendimento atendimento;
                String strId;
                int id;
                switch (action) {
                    case "create":
                        List<TipoAtendimento> listaTiposAtendimentos = TipoAtendimentoFacade.buscarTodosTiposAtendimentos();
                        List<Produto> listaProdutos = ProdutoFacade.buscarTodos();                  
                        Usuario user = LoginFacade.buscarUsuarioPorId(loginBean.getId());
                        request.setAttribute("listaTiposAtendimentos", listaTiposAtendimentos);
                        request.setAttribute("listaProdutos", listaProdutos);                        
                        request.setAttribute("user", user);
                        rd = getServletContext().getRequestDispatcher("/atendimento.jsp");
                        rd.forward(request, response);
                    break;
                    case "new":
                        atendimento = new Atendimento();
                        int idTipoAtendimento = Integer.parseInt(request.getParameter("tipoAtendimento"));
                        TipoAtendimento tipoAtendimento = new TipoAtendimento();
                        tipoAtendimento.setIdTipoAtendimento(idTipoAtendimento);
                        int idProduto = Integer.parseInt(request.getParameter("produto"));
                        Produto p = new Produto();
                        p.setIdProduto(idProduto);
                        int idCliente = loginBean.getId();
                        Usuario c = new Usuario();
                        c.setId(idCliente);
                        atendimento.setDescricaoAtendimento(request.getParameter("descricao"));
                        String data = request.getParameter("dataAtendimento");
                        LocalDateTime date = AtendimentoFacade.converterStrToLocalDate(data);
                        atendimento.setDataHoraAtendimento(date);

                        if (atendimento != null) {
                            AtendimentoFacade.inserirAtendimento(atendimento);
                            listaAtendimentos = AtendimentoFacade.buscarTodosAtendimentosUser(loginBean.getId());
                            if (listaAtendimentos != null) {
                                request.setAttribute("meusAtendimentos", listaAtendimentos);
                                rd = getServletContext().getRequestDispatcher("/meusAtendimentos.jsp");
                                rd.forward(request, response);
                            }
                        }
                        break;
                    case "list":
                        listaAtendimentos = AtendimentoFacade.buscarTodosAtendimentosUser(loginBean.getId());
                        if (listaAtendimentos != null) {
                            request.setAttribute("meusAtendimentos", listaAtendimentos);
                            rd = getServletContext().getRequestDispatcher("/meusAtendimentos.jsp");
                            rd.forward(request, response);
                        } else
                            response.sendRedirect("/atendimento.jsp");
                    break;
                    case "show":
                        strId = request.getParameter("id");
                        id = Integer.parseInt(strId);
                        atendimento = AtendimentoFacade.buscarAtendimentoPorId(id);
                        if (atendimento != null) {
                            request.setAttribute("atendimento", atendimento);
                            rd = getServletContext().getRequestDispatcher("/atendimentoDetalhe.jsp");
                            rd.forward(request, response);
                        }
                    break;
                    case "remove":
                        strId = request.getParameter("id");
                        id = Integer.parseInt(strId);
                        AtendimentoFacade.removerAtendimento(id);
                        rd = getServletContext().getRequestDispatcher("/AtendimentoServlet?action=list");
                        rd.forward(request, response);
                    break;
                    case "listarAbertos":
                        listaAtendimentos = AtendimentoFacade.buscarTodosAtendimentosAbertos();
                        if (listaAtendimentos != null) {
                            request.setAttribute("atendimentosEmAberto", listaAtendimentos);
                            rd = getServletContext().getRequestDispatcher("/funcionarioAtendimento.jsp");
                            rd.forward(request, response);
                        }
                    break;
                    case "showFunc":
                        strId = request.getParameter("id");
                        id = Integer.parseInt(strId);
                        atendimento = AtendimentoFacade.buscarAtendimentoPorId(id);
                        if (atendimento != null) {
                            request.setAttribute("atendimento", atendimento);
                            rd = getServletContext().getRequestDispatcher("/responderAtendimento.jsp");
                            rd.forward(request, response);
                        }
                    break;
                    case "resp":
                        strId = request.getParameter("id");
                        id = Integer.parseInt(strId);
                        String s = request.getParameter("solucao");
                        AtendimentoFacade.responderAtendimento(id, s);
                        listaAtendimentos = AtendimentoFacade.buscarTodosAtendimentosAbertos();
                        if (listaAtendimentos != null) {
                            request.setAttribute("atendimentosEmAberto", listaAtendimentos);
                            rd = getServletContext().getRequestDispatcher("/funcionarioAtendimento.jsp");
                            rd.forward(request, response);
                        }
                    break;
                    case "listarTodosAtendimentos":
                       listaAtendimentos = AtendimentoFacade.buscarTodosAtendimentos();
                       if (listaAtendimentos != null) {
                         request.setAttribute("todosAtendimentos", listaAtendimentos);
                         rd = getServletContext().getRequestDispatcher("/listarTodosAtendimentos.jsp");
                         rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
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

