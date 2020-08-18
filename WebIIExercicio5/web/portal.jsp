<%@page import="com.ufpr.tads.web2.beans.LoginBean" %>

<%
  if (session == null || session.getAttribute("login") == null) {
      request.setAttribute("msg", "Realize a autenticação");
      request.setAttribute("page", request.getContextPath() + "/");
      try {
          request.getRequestDispatcher("erro.jsp").forward(request, response);
          return;
      } catch (ServletException e) {
          e.getStackTrace();
      }
  }

  LoginBean usuario = (LoginBean) session.getAttribute("login");
%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lan="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" />
    <title>
      Exercício 4
    </title>
  </head>
  <body>
    <header>
      <div>
        <div>
          <div>
            <div>
              <div>
                <h2>
                  Portal
                </h2>
              </div>
              <div>
                <a href="<%= request.getContextPath() %>/LogoutServlet">
                  Logout
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>

    <div>
      <div>
        <h3>
          Usuário Logado: <%= usuario.getNomeUsuario() %>
        </h3>
        <a href="inserir.jsp">
          Inserir Usuário &gt;&gt;&gt;
        </a>
        <div>
            Em caso de problemas contactar o administrador:
            <jsp:useBean class="com.ufpr.tads.web2.beans.ConfigBean" scope="application"  id="configuracao"/>
            <a href="mailto:<jsp:getProperty name="configuracao" property="emailAdmin" />">
              <jsp:getProperty name="configuracao" property="emailAdmin" />
            </a>
        </div>
      </div>
    </div>
  </body>
</html>
