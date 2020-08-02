/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpr.ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("<style> table,td {border:1px solid black; border-collapse:collapse;} </style>");
            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            
            
            out.println("<h1 style='color: #0320fc; font-family: Verdana; font-size:3em;'>Setor de Educação Profissional Tecnologica</h1>");
            out.println("<h1 style='color: #000000; font-family: Arial; font-size:1em;'>Alcides Vieira Arcoverde, 1225 ZIP Code: 81520-260 - Jardim das Américas - Curitiba (PR) - Brazil</h1>");
            
            
            out.println("<table>");

            ArrayList<String> courses = new ArrayList();
            
            courses.add("<tr><td>TADS</td><td>http://www.sept.ufpr.br/portal/analisesistemas/</td></tr>");
            courses.add("<tr><td>ESPECIALIZACAO EM ENGENHARIA DE SOFTWARE</td><td>http://www.engenhariadesoftware.ufpr.br:28080/engenhariadesoftware/</td></tr>");
            courses.add("<tr><td>ESPECIALIZAÇÃO EM INTELIGÊNCIA ARTIFICIAL APLICADA</td><td>http://www.iaa.ufpr.br/</td></tr>");
            courses.add("<tr><td>Técnico em Agente Comunitário de Saúde</td><td>http://www.sept.ufpr.br/portal/agentesaude/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnologia em Gestão Pública</td><td>http://www.sept.ufpr.br/portal/gestaopublica/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnólogo em Secretariado</td><td>http://www.sept.ufpr.br/portal/secretariado/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnologia em Luteria</td><td>http://www.sept.ufpr.br/portal/luteria/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnologia em Comunicação Institucional</td><td>http://www.sept.ufpr.br/portal/comunicacaoinstitucional/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnologia em Negócios Imobiliários</td><td>http://www.sept.ufpr.br/portal/negociosimobiliarios/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnologia em Gestão da Qualidade</td><td>http://www.sept.ufpr.br/portal/gestaoqualidade/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Técnico em Petróleo</td><td>http://www.sept.ufpr.br/portal/petroleogas/sobre-o-curso/</td></tr>");
            courses.add("<tr><td>Tecnologia em Produção Cênica - SEPT</td><td>http://www.sept.ufpr.br/portal/producaocenica/</td></tr>");
            
            int n = courses.size();
            
            for(String showCourse: courses){
                out.println(showCourse);
            }
            
            out.println("</table>");
            
            out.println("<a href='http://localhost:8080/WebIIExercicio1/ufpr.ex1/Meu%20TADS.jsp'>Meu Tads</a>");
            
            out.println("</body>");
            out.println("</html>");
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
