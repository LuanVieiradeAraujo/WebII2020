<%-- 
    Document   : portal
    Created on : 24/08/2020, 16:56:58
    Author     : Carolina
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ufpr.tads.web2.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    if (session.getAttribute("login") == null) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

        rd.forward(request, response);
    } else {%>



<html>
    <head>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        -->
        <!-- Optional theme -->
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        -->


        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Lista de Clientes</title>
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
                    <a class="navbar-brand" href="portal.jsp">Portal</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="ClientesServlet?action=">Cadastro de Clientes</a></li>
                        <li><a href="LogoutServlet">Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="main" class="container-fluid" style="margin-top: 50px">

            <div  class="row">
                <div class="col-sm-3">
                    <h2>Clientes</h2>
                </div>
                <div class="col-sm-6">

                </div>
                <div class="col-sm-3">
                    <a href="ClientesServlet?action=formNew" class="btn btn-primary pull-right h2">Novo Cliente</a>
                </div>
            </div> <!-- /#top -->


            <hr />
            <div id="list" class="row">
                <div class="table-responsive col-md-12">
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th>CPF</th>
                                <th>Nome</th>
                                <th>E-mail</th>

                                <th class="actions">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach  var="c" items="${lista}" > 
                            <tr>
                                <td>${c.cpfCliente}</td>
                                <td>${c.getNomeCliente()}</td>    
                                <td>${c.getEmailCliente()}</td>
                                <td class="actions">
                                    <a class="btn btn-success btn-xs" href="ClientesServlet?action=show&id=${c.getIdCliente()}">Visualizar</a>
                                    <a class="btn btn-warning btn-xs" href="ClientesServlet?action=formUpdate&id=${c.getIdCliente()}">Editar</a>
                                    <a class="btn btn-danger btn-xs"  href="ClientesServlet?action=remove&id=${c.getIdCliente()}" >Excluir</a>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div> <!-- /#list -->


        </div> <!-- /#main -->

        <!-- Modal -->
        <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="modalLabel">Excluir Item</h4>
                    </div>
                    <div class="modal-body">
                        Deseja realmente excluir este item?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

    <footer class="footer navbar-fixed-bottom">

        Em caso de problemas contatar o administrador: ${configuracao.email}
    </footer>
</html>
<%}%>
