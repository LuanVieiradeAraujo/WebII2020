<%-- 
    Document   : clientesNovo
    Created on : 23/08/2020, 11:06:38
    Author     : Carolina
--%>
<%@ page errorPage="erro.jsp" %>
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
        <div id="main" class="container-fluid">

            <h3 class="page-header"></h3>
            <h3 class="page-header">Adicionar Cliente</h3>
            <form action="ClientesServlet?action=new" method="post">
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" name="nome" placeholder="Digite o nome">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputCPF">CPF</label>
                        <input type="text" class="form-control"  name="cpf" placeholder="Digite o CPF">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputEmail1">E-mail</label>
                        <input type="email" class="form-control"  name="email" placeholder="Digite o E-mail">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-sm-2">
                        <label for="data">Data</label>
                        <input type="date" class="form-control"  name="data" >
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputRua">Rua</label>
                        <input type="text" class="form-control"  name="rua" placeholder="Digite a rua">
                    </div>
                    <div class="form-group col-md-1">
                        <label for="inputNumero">Número</label>
                        <input type="text" class="form-control"  name="numero" placeholder="Digite o número">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputCEP">CEP</label>
                        <input type="text" class="form-control"  name="cep" placeholder="Digite o CEP">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-5">
                        <label for="inputCidade">Cidade</label>
                        <input type="text" class="form-control"  name="cidade" placeholder="Digite a Cidade">
                    </div>
                    <div class="form-group col-md-1">
                        <label for="inputUF">UF</label>
                        <input type="text" class="form-control"  name="uf" placeholder="Digite o UF">
                    </div>
                </div>

                

                <hr />

                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a href="ClientesServlet?action=" class="btn btn-default">Cancelar</a>
                    </div>
                </div>

            </form>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

    <footer class="footer navbar-fixed-bottom">

        Em caso de problemas contatar o administrador: ${configuracao.email}
    </footer>
</html>

