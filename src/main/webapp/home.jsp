<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Verifica se o usuário está logado --%>
<%
    if (session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Início - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            background-color: #007bff;
            color: #ffffff;
            font-family: 'Roboto', sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }

        .navbar {
            background-color: #ffffff;
            padding: 1rem;
            width: 100%;
            display: flex;
            justify-content: space-between;
        }

        .navbar-brand {
            color: #007bff !important;
            font-weight: bold;
        }

        .nav-link {
            color: #007bff !important;
            font-weight: bold;
        }

        .hero-section {
            text-align: center;
            padding: 30px 20px;
            flex-shrink: 0;
        }

        .hero-section h1 {
            font-size: 2rem;
            font-weight: 700;
            color: #000000;
        }

        .hero-section h1 span {
            color: #ffffff;
        }

        .hero-section p {
            font-size: 1rem;
            margin-bottom: 20px;
        }

        .hero-section .btn-dashboard {
            background-color: #ffffff;
            color: #007bff;
            font-size: 1.1rem;
            font-weight: 700;
            padding: 10px 20px;
            border-radius: 50px;
        }

        .feature-cards {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 15px;
            flex-wrap: wrap;
            max-width: 100%;
            padding: 20px;
            flex-shrink: 0;
        }

        .card {
            width: 220px;
            height: 280px;
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            overflow: hidden;
            padding: 20px;
            background-color: #ffffff;
            color: #007bff;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
        }

        .card img {
            width: 50px;
            height: 50px;
            margin: 0 auto 10px;
        }

        .card p {
            font-size: 0.9rem;
            color: #007bff;
            flex-grow: 1;
        }

        .btn-card {
            width: 180px;
            height: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0 auto;
            border-radius: 50px;
            font-size: 0.9rem;
            color: #ffffff;
            text-align: center;
        }

        .btn-primary.btn-card { background-color: #007bff; }
        .btn-danger.btn-card { background-color: #dc3545; }
        .btn-info.btn-card { background-color: #17a2b8; }
        .btn-warning.btn-card { background-color: #ffc107; color: #212529; }
        .btn-success.btn-card { background-color: #28a745; }

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
    <a class="navbar-brand" href="home.jsp">Fintech</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sair</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container hero-section">
    <h1>Bem-vindo à <span>Fintech!</span></h1>
    <p>Sua plataforma completa para gerenciamento financeiro pessoal. Transforme seus objetivos em realidade.</p>
    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-dashboard">Acessar Dashboard</a>

</div>

<div class="feature-cards">
    <div class="card">
        <img src="${pageContext.request.contextPath}/image/Conta.svg" alt="Icone Gerenciar Conta">
        <p>Gerencie suas contas bancárias e acompanhe o saldo disponível.</p>
        <a href="conta.jsp" class="btn btn-primary btn-card">Gerenciar Conta</a>
    </div>

    <div class="card">
        <img src="image/Despesa.svg" alt="Icone Gerenciar Despesas">
        <p>Controle suas despesas e mantenha o seu orçamento sob controle.</p>
        <a href="despesa.jsp" class="btn btn-danger btn-card">Gerenciar Despesas</a>
    </div>

    <div class="card">
        <img src="image/Receita.svg" alt="Icone Gerenciar Receitas">
        <p>Registre suas receitas para um acompanhamento financeiro completo.</p>
        <a href="receita.jsp" class="btn btn-info btn-card">Gerenciar Receitas</a>
    </div>

    <div class="card">
        <img src="image/Investimento.svg" alt="Icone Gerenciar Investimentos">
        <p>Monitore seus investimentos e acompanhe seu crescimento financeiro.</p>
        <a href="invest.jsp" class="btn btn-warning btn-card">Gerenciar Investimentos</a>
    </div>

    <div class="card">
        <img src="image/Meta.svg" alt="Icone Gerenciar Metas">
        <p>Estabeleça metas financeiras e acompanhe seu progresso.</p>
        <a href="objetivo.jsp" class="btn btn-success btn-card">Gerenciar Metas</a>
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
