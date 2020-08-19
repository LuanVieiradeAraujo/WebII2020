<%-- 
    Document   : erro
    Created on : Aug 18, 2020, 4:47:58 PM
    Author     : User
--%>

    <%
        if (request.getParameter("msg") != null &&  request.getParameter("erro") != null){
            out.println("<a style='color:red;'>" 
                    + "Erro: <b>" 
                    + request.getParameter("erro")
                    + "</b>!</a>");
        }
        
        request.setAttribute("msg", "Dados de login inválidos.");
        request.setAttribute("page", request.getContextPath() + "/");
        request.getRequestDispatcher("index.jsp").forward(request, response);



    %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
