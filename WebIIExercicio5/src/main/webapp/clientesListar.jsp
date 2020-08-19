<%-- 
    Document   : portal
    Created on : Aug 18, 2020, 4:18:32 PM
    Author     : User
--%>

    <%@page import="java.util.List"%>
<%@page import="com.ufpr.tads.web2.ex5.beans.Cliente"%>
<%
        if (request.getSession() != null){
                request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
                request.setAttribute("page", request.getContextPath() + "/");
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <h1>Hello World!</h1>
        
<jsp:useBean id="loginBean" class="com.ufpr.tads.web2.ex5.beans.LoginBean"></jsp:useBean>
<jsp:getProperty property="loginBean" name="nomeUsuario"/><br> 

    <%
               
       String[] clientes = request.getParameterValues("clientes");
       
       for (String str : clientes) {
        System.out.print(str);
       }
        
        if (application.getAttribute("email") != null) {
            out.println("<a>"
            + "Em caso de problemas, entre em contato com o administrador<b>"
            + application.getAttribute("email")
            + "</b>!</a>");
        }
        else{
            out.println("<a>Email do administrador nao preenchido no escopo da aplicacao</a>");
        };
    %>
        
    
</html>
