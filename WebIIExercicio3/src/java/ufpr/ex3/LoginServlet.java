package ufpr.ex3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
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
        
        boolean logado = false, valid = false;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        HttpSession session = request.getSession(false);
        
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
            st = con.prepareStatement("select login_usuario, senha_usuario, nome_usuario from tb_usuario where login_usuario = '" + login + "' and senha_usuario = '" + senha + "'");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        while(rs.next()) {
            String nomeDb = rs.getString("nome_usuario");
            String loginDb = rs.getString("login_usuario");
            String senhaDb = rs.getString("senha_usuario");
            
            logado = true;
            session = request.getSession();
            session.setAttribute("usuarioLogado", new Usuario(nomeDb, loginDb, senhaDb));
            valid = true;
            break;
        }
        
        if (!valid){
            if (login == null && senha == null && session != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/portal");
                rd.forward(request, response);
                return;
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            if (logado) {
                out.println("Usuario logado com sucesso");
                out.println("<a href='http://localhost:8080/PortalServlet'>Ir para PortalServlet</a>");
            }
            else{
                out.println("Usuario/senha nao encontrado");
                out.println("<a href='http://localhost:8080/index.html'>Ir para Index</a>");
            }
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
