<%-- 
    Document   : erro
    Created on : Aug 18, 2020, 4:47:58 PM
    Author     : User
--%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Exception</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
      <a class="navbar-brand" href="#">Start Bootstrap</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
              <%
        if (request.getParameter("msg") != null &&  request.getParameter("erro") != null){
            out.println("<h1 class='mt-5'>" 
                    + "Erro: <b>" 
                    + request.getParameter("erro")
                    + "</b>!</a>");
        }
        
        request.setAttribute("msg", "Dados de login inválidos.");
        request.setAttribute("page", request.getContextPath() + "/");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    %>
        <ul class="list-unstyled">
          <li>Bootstrap 4.5.0</li>
          <li>jQuery 3.5.1</li>
        </ul>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.slim.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <%
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
  
</body>

</html>
