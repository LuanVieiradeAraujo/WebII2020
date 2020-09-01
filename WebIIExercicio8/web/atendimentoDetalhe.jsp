<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/validationLoginBean.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
                <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
        <jsp:include page="/head.jsp"/></head>
    <body>
        <jsp:include page="/nav.jsp" />

        <div class='d-flex justify-content-center m-5'>
            <div class='align-self-center'>
               <h4>Dados do atendimento</h4>
            </div>
        </div>

        <div class='container mt-5 d-flex justify-content-center'>
            <table class="table w-50">
                <tr>
                    <td><c:out value="Id :"/></td>
                    <td><c:out value="${atendimento.idAtendimento}"/></td>
                </tr>
                <tr>
                    <td><c:out value="Data de atendimento :"/></td>
                    <td>
                        <fmt:parseDate value="${ atendimento.dataHoraAtendimento }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
                    </td>
                </tr>
                <tr>
                    <td><c:out value="Atendimento Resolvido:"/></td>
                    <td><c:out value="${atendimento.situacao}"></c:out></td>
                    </tr>
                    <tr>
                        <td><c:out value="Descrição :"/></td>
                    <td><c:out value="${atendimento.descricaoAtendimento}"/></td>
                </tr>
                <tr>
                    <td><c:out value="Tipo de atendimento :"/></td>
                    <td><c:out value="${atendimento.tipoAtendimento.nomeTipoAtendimento}"/></td>
                </tr>
                <tr>
                    <td><c:out value="Produto :"/></td>
                    <td><c:out value="${atendimento.produto.nomeProduto}"/></td>
                </tr>
                <tr>
                    <td><c:out value="Nome do cliente"/></td>
                    <td><c:out value="${atendimento.usuario.nomeUsuario}"/></td>
                </tr>
                <tr>
                    <td><c:out value="Solução Apresentada p/ atendimento"/></td>
                    <td><c:out value="${atendimento.solucaoApresentada}"/></td>
                </tr>


            </table>
        </div>

        <div class="d-flex justify-content-center">
            <a href='AtendimentoServlet?action=list' class='btn btn-primary col-1 ml-2' style='float: right; '>Voltar</a>
        </div>

        <jsp:include page="/footer.jsp" />
        <jsp:include page="/modalRemove.jsp" />

    </body>
</html>

<script>
    $("#save").click(function() {

        var idAtendimento = $("#idAtendimento").val();
        var id = "action=remove" + "&id=" + idAtendimento;

        $('#myModal').modal('hide');
        $.ajax({
            type: "GET",
            url: "AtendimentoServlet",
            data: id,
            success: function(result) {
                window.location.replace("/Trabalho-Web/AtendimentoServlet?action=list");
            },
            error: function(error) {
                console.log("error" + error);
            }
        });
    });</script>
