<%@ page errorPage="erro.jsp" %>
<%@include file="/validationLoginBean.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="container">
            <div class="row">

                <div class='container col-md-6 text-center'>
                    <div class='align-self-center m-5'>
                        <h4>Todos os atendimentos realizados até o momento</h4>
                        <c:if test="${empty todosAtendimentos}">
                            <h2>Não há atendimentos</h2>
                        </c:if>
                    </div>
                </div>

                <div class="col-md-12 col-md-offset-2">
                    <div class='d-flex justify-content-center m-5'>
                        <c:if test="${!empty todosAtendimentos}">
                            <table class=' table  text-center'>
                                <tr>
                                    <th>Data/hora</th>
                                    <th>Produto</th>
                                    <th>Nome do cliente</th>
                                    <th>Descrição</th>
                                     <c:if test="${loginBean.tipoUsuario == 'f'}">
                                       <th>Detalhes Atendimento</th>
                                     </c:if>
                                </tr>
                                <c:forEach items="${todosAtendimentos}" var="atendimento">
                                    <tr style="${atendimento.prioridade eq 1 and atendimento.situacao eq 'N' ? 'background-color:#FFFFE0' : 'background-color :#FFFFE0  '}" >
                                        <td id="dataAbertura"><fmt:parseDate value="${ atendimento.dataHoraAtendimento }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
                                        </td>
                                        <td><c:out value="${atendimento.produto.nomeProduto}" /></td>
                                        <td><c:out value="${atendimento.usuario.nomeUsuario}"/></td>
                                        <td><c:out value="${atendimento.descricaoAtendimento}"/></td>
                                        <td>
                                            <c:if test="${loginBean.tipoUsuario == 'f'}">
                                                <a class="mr-3" href="AtendimentoServlet?action=showFunc&id=${atendimento.idAtendimento}"><button><i class="fas fa-comment-dots fa-2x"></i></button></a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${!empty msg}">
            <div style="margin-left: 37%" class=" w-25 d-flex justify-content-center alert alert-success alert-dismissible fade show" role="alert">
                <strong><c:out value="${msg}"/> </strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

        </c:if>
        <div>
            <jsp:include page="/footer.jsp" />
        </div>
    </body
</html>

