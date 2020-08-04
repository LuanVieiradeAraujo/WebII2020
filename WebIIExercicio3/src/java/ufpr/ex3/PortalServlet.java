package ufpr.ex3;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

@WebServlet(urlPatterns = {"/PortalServlet"})
public class PortalServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        boolean logado = true;
        HttpSession session = request.getSession(false);
        if (session == null)
           logado = false;
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (!logado) {
                String errMsg = "Você não está logado!";
                String page = request.getContextPath()+ "/";
                request.setAttribute("errMsg", errMsg);
                request.setAttribute("page", page);
                RequestDispatcher rd = request.getRequestDispatcher("/ErroServlet");
                rd.forward(request, response);
            }
            
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            String dbUser = "postgres";
            String dbSenha = "*damiani*tech*";
            //String dbSenha = "postgres";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            
            try {
                con = DriverManager.getConnection(url, dbUser, dbSenha);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                st = con.prepareStatement("select login_usuario, senha_usuario, nome_usuario from tb_usuario");
                rs = st.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            List<Usuario> usuarios = new ArrayList<>();
            
            while(rs.next()) {
                String nomeDb = rs.getString("nome_usuario");
                String loginDb = rs.getString("login_usuario");
                String senhaDb = rs.getString("senha_usuario");
                
                usuarios.add(new Usuario(nomeDb, loginDb, senhaDb));
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PortalServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"CadastrarUsuarioServlet\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"nome\" value=\"\" placeholder=\"nome\">");
            out.println("<input type=\"text\" name=\"login\" value=\"\" placeholder=\"login\">");
            out.println("<input type=\"text\" name=\"senha\" value=\"\" placeholder=\"senha\">");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("<table>");
            
            out.println("<th colspan=3>Usuarios</th>");
            out.println("<tr>");
            out.println("<td>Nomes</td>");
            out.println("<td>Logins</td>");
            out.println("<td>Senhas</td>");
            out.println("</tr>");
            
            if(usuarios == null)
                usuarios = new ArrayList();
            
            for(Usuario usuario: usuarios){
                out.println("<tr>");
                out.println("<td>" + usuario.getNome() + "</td>");
                out.println("<td>" + usuario.getLogin() + "</td>");
                out.println("<td>" + usuario.getSenha() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<a href='http://localhost:8080/LogoutServlet'>Logout Servlet</a>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PortalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PortalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
