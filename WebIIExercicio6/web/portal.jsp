<%-- 
    Document   : portal
    Created on : 23/08/2020, 16:56:58
    Author     : Carolina
--%>
<%@ page errorPage="erro.jsp" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ufpr.tads.web2.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<c:if test = "${login eq null}">
<jsp:forward  page="index.jsp">
    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema."/>
</jsp:forward>
</c:if>



<html>
    <head>
      <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        -->
        <!-- Optional theme -->
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">
        
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Portal</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Portal</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="ClientesServlet?action=">Cadastro de Clientes</a></li>
                        <li><a href="LogoutServlet">Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="main" class="container-fluid">          
                <h3 class="page-header"></h3>
            <jsp:useBean id="login" class="com.ufpr.tads.web2.beans.LoginBean" scope="session"/>
            <p>Nome: ${login.nome}</p>
        </div>

    </body>
    <footer class="footer navbar-fixed-bottom">

        Em caso de problemas contatar o administrador: ${configuracao.email}
    </footer>
</html>

