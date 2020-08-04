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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/CadastrarUsuarioServlet"})
public class CadastrarUsuarioServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        List<String> erros = new ArrayList<>();
        if (nome == null || nome.equals("")) {
            erros.add("O campo NOME é obrigatório.");
        }
        if (login == null || login.equals("")) {
            erros.add("O campo LOGIN é obrigatório.");
        }
        if (senha == null || senha.equals("")) {
            erros.add("O campo SENHA é obrigatório.");
        }

        try (PrintWriter out = response.getWriter()) {
            if (erros.size() > 0) {
                erros.forEach(erro -> out.print("<div>" + erro + "</div>"));
            } else {
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
                    st = con.prepareStatement("select login_usuario, senha_usuario, nome_usuario from tb_usuario");
                    rs = st.executeQuery();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                List<Usuario> listaUsuarios = new ArrayList<>();
                while (rs.next()) {
                    try {
                        String loginDb = rs.getString("login_usuario");
                        String senhaDb = rs.getString("senha_usuario");
                        String nomeDb = rs.getString("nome_usuario");
                        listaUsuarios.add(new Usuario(nomeDb, loginDb, senhaDb));
                    } catch (SQLException ex) {
                        Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    st = con.prepareStatement("insert into tb_usuario(login_usuario, senha_usuario, nome_usuario) values (?, ?, ?)");
                    st.setString(1, login);
                    st.setString(2, senha);
                    st.setString(3, nome);
                    st.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Usuario usuario = new Usuario(nome, login, senha);
                listaUsuarios.add(usuario);
                session.setAttribute("listaUsuarios", listaUsuarios);
                out.print("<div>Inserido com sucesso!</div>");
            }
            out.print("Portal Servlet</div>");
            out.println("<div>Voltar para<a href='http://localhost:8080/PortalServlet'>Portal Servlet</a></div>");
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
