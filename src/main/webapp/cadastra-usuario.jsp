<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Verifica se há uma mensagem de erro antes de carregar a página --%>
<%
  String mensagemErro = (String) request.getAttribute("mensagemErro");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cadastro de Usuário - Fintech</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-color: #e0e0e0; /* Fundo cinza claro */
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 100vh;
      margin: 0;
    }

    .navbar {
      background-color: #4A90E2; /* Azul forte */
      width: 100vw;
      margin-bottom: 20px;
    }

    .navbar-brand, .nav-link {
      color: white !important;
      font-weight: bold;
    }

    .navbar-brand {
      font-size: 1.5rem;
    }

    .card-cadastro {
      width: 100%;
      max-width: 400px;
      border-radius: 10px;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
      padding: 2rem;
      background-color: white;
    }

    .btn-primary {
      background-color: #4A90E2;
      border: none;
    }

    .btn-primary:hover {
      background-color: #357ABD;
    }

    footer {
      margin-top: auto;
      background-color: #e0e0e0;
      padding: 1rem;
      text-align: center;
      width: 100%;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">Fintech</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/home.jsp">Início</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/about.jsp">Sobre Nós</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Login</a>
      </li>
    </ul>
  </div>
</nav>

<div class="card card-cadastro">
  <h2 class="text-center mb-4">Cadastro de Usuário</h2>
  <p class="text-center text-muted">Preencha o formulário abaixo para criar uma nova conta.</p>

  <% if (mensagemErro != null && !mensagemErro.isEmpty()) { %>
  <div class="alert alert-danger text-center" role="alert">
    <%= mensagemErro %>
  </div>
  <% } %>

  <form action="${pageContext.request.contextPath}/cadastro" method="post">
    <div class="form-group">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu nome" required>
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
    </div>
    <div class="form-group">
      <label for="senha">Senha</label>
      <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
    </div>
    <button type="submit" class="btn btn-primary btn-block mt-4">Cadastrar</button>
  </form>
</div>

<footer>
  <p>&copy; 2024 Fintech. Todos os direitos reservados.</p>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
