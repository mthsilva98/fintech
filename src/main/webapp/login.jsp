<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #007bff;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            color: #ffffff;
        }

        .navbar {
            background-color: #ffffff;
            width: 100%;
            padding: 1rem;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
            border-bottom: 1px solid #ddd;
        }

        .navbar-brand, .nav-link {
            color: #007bff !important;
            font-weight: bold;
            font-size: 1.2rem;
        }

        .login-section {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 2rem;
            width: 100%;
            max-width: 1200px;
            padding-top: 6rem; /* Offset for fixed navbar */
            color: #ffffff;
        }

        .login-section img {
            width: 300px;
            margin-right: 30px; /* Move the lamp icon slightly to the left */
        }

        .card-login {
            width: 100%;
            max-width: 400px;
            padding: 2rem;
            background-color: #1e90ff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            color: #ffffff;
        }

        .card-login h2 {
            font-size: 2rem;
            font-weight: bold;
            text-align: center;
            margin-bottom: 1rem;
        }

        .card-login h4 {
            font-weight: bold;
            color: #ffffff;
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .form-control {
            background-color: #e9f5ff;
            border: none;
            padding: 0.75rem;
            border-radius: 5px;
            font-size: 1rem;
            color: #007bff;
        }

        .form-control::placeholder {
            color: #007bff;
            opacity: 1;
        }

        .form-group label {
            color: #ffffff;
            font-weight: bold;
            font-size: 1rem;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            font-weight: bold;
            padding: 0.75rem;
            border-radius: 5px;
            font-size: 1.1rem;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .text-muted {
            color: #ffffff !important;
            font-size: 0.9rem;
        }

        .text-muted a {
            color: #ffffff;
            font-weight: bold;
            text-decoration: none;
        }

        .text-muted a:hover {
            text-decoration: underline;
        }

        .side-text {
            max-width: 300px;
            text-align: center;
            color: #ffffff;
            font-size: 1.5rem;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.5;
            font-weight: 500;
            margin-left: 50px; /* Move further to the right */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* Shadow effect */
        }

        footer {
            background-color: #f8f9fa;
            color: #212529;
            padding: 10px;
            text-align: center;
            margin-top: auto;
            width: 100%;
            position: relative;
            bottom: 0;
            flex-shrink: 0;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">Fintech</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cadastra-usuario.jsp">Cadastrar</a>
            </li>
        </ul>
    </div>
</nav>

<div class="login-section">
    <img src="${pageContext.request.contextPath}/image/lampada.svg" alt="Ícone de lâmpada com cérebro e símbolo de dinheiro">
    <div class="card card-login">
        <h2>Fintech</h2>
        <h4>Bem-vindo de volta!</h4>
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu e-mail" required>
            </div>
            <div class="form-group">
                <label for="senha">Senha</label>
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block mt-4">Entrar</button>
        </form>
        <div class="text-center mt-3 text-muted">
            <p>Não tem uma conta? <a href="${pageContext.request.contextPath}/cadastra-usuario.jsp">Cadastre-se aqui</a>.</p>
        </div>
    </div>
    <div class="side-text">
        <p>Conquiste seus objetivos financeiros com segurança e simplicidade. Faça login e comece agora!</p>
    </div>
</div>

<footer>
    <p>&copy; 2024 Fintech. Todos os direitos reservados.</p>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
