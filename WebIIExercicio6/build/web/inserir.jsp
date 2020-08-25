<%-- 
    Document   : inserir
    Created on : 25/08/2020, 10:56:19
    Author     : Carolina
--%>
<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    
    if (session.getAttribute("login") == null) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
        request.setAttribute("page", "index.html");
        rd.forward(request, response);
    } else {%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action = 'CadastrarUsuarioServlet' method = 'POST'>
            Nome: <input type = "text" name = "nome"/> <br/>
            Login: <input type = "text" name = "log"/> <br/>
            Senha: <input type = "text" name = "senha"/> <br/>
            <input type = "submit" value = "Salvar">
        </form>
    </body>
    <footer>

        Em caso de problemas contactar o administrador: ${configuracao.email}
    </footer>
</html>
<%}%>