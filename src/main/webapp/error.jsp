<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erro - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home.jsp">Fintech</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="home.jsp">Início</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about.jsp">Sobre Nós</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="login.jsp">Login</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center text-danger">Ops! Algo deu errado.</h2>
    <p class="text-center">Ocorreu um erro inesperado em nosso sistema. Tente novamente mais tarde ou entre em contato com o suporte.</p>

    <div class="alert alert-danger">
        <h5>Detalhes do Erro:</h5>
        <p><strong>Mensagem:</strong> ${mensagemErro}</p>
        <p><strong>Tipo:</strong> ${tipoErro}</p>
        <pre>${pageContext.exception}</pre>
    </div>

    <div class="text-center mt-4">
        <a href="home.jsp" class="btn btn-primary">Voltar ao Início</a>
    </div>
</div>

<footer class="bg-light text-center text-lg-start mt-5">
    <div class="container p-4">
        <p class="text-center">&copy; 2024 Fintech. Todos os direitos reservados.</p>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
