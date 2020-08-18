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
    <div>
      <div>
        <h3>
          <%= request.getAttribute("msg") %>
        </h3>
        <div>
            <a href="<%= request.getAttribute("page") %>">
              Acessar a tela de login
            </a>
        </div>
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
