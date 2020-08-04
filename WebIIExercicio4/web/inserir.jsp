<%@page import="com.ufpr.tads.web2.beans.LoginBean" %>

<%
  if (session == null || session.getAttribute("login") == null) {
      request.setAttribute("msg", "Realize a autenticação");
      request.setAttribute("page", request.getContextPath() + "/");
      try {
          request.getRequestDispatcher("erro.jsp").forward(request, response);
          return;
      } catch (ServletException e) {
          e.getStackTrace();
      }
  }

  LoginBean usuario = (LoginBean) session.getAttribute("login");
%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lan="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport"/>
    <title>
      Exercício 4
    </title>
  </head>
  <body>

    <header>
      <div>
        <div>
          <div>
            <h2>
              Inserir
            </h2>
          </div>
          <div class="col-2">
              <a href="<%= request.getContextPath() %>/LogoutServlet">
                  Logout
              </a>
          </div>
        </div>
      </div>
    </header>

    <main>
      <div>
        <div>
          <div>
            <h3>
              Cadastro de Usuário
            </h3>
            <form>
              <input type="hidden" name="id" id="id" />
              <div>
                <label for="nome">Nome do usuário</label>
                <input
                  type="text"
                  id="nome"
                  class="form-control"
                  name="nome"
                  placeholder="Insira um nome"
                  autofocus
                />
              </div>
              <div>
                <label for="login">Login de acesso</label>
                <input
                  type="text"
                  id="login"
                  class="form-control"
                  name="login"
                  placeholder="Insira um login"
                />
              </div>
              <div class="form-group">
                <label for="senha">Senha de acesso</label>
                <input
                  type="password"
                  id="senha"
                  class="form-control"
                  name="senha"
                  placeholder="Insira uma senha"
                />
              </div>
              <button type="submit" id="btn-ok">
                Cadastrar
              </button>
              <button type="reset" id="btn-cancel">
                Cancelar
              </button>
            </form>
          </div>
        </div>
      </div>

      <div>
        <div>
          <table>
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nome</th>
                <th scope="col">Login</th>
                <th scope="col">Senha</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
      </div>
    </main>
  </body>
</html>
