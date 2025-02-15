<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sobre - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            background-color: #007bff; /* Fundo azul */
            display: flex;
            flex-direction: column;
            color: #ffffff;
            font-family: 'Roboto', sans-serif; /* Fonte mais elegante */
        }

        .navbar {
            background-color: #ffffff;
            padding: 1rem;
            width: 100%;
        }

        .navbar-brand, .nav-link {
            color: #007bff !important;
            font-weight: bold;
        }

        .content-wrapper {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            padding: 60px 20px;
        }

        .content-wrapper h1 {
            font-size: 3rem; /* Aumentei o tamanho do título principal */
            font-weight: 700;
            color: #000000;
            margin-bottom: 40px; /* Mais espaço abaixo do título principal */
        }

        .content-wrapper p.lead {
            font-size: 1.3rem; /* Aumentei o tamanho do subtítulo */
            color: #ffffff;
            margin-bottom: 80px; /* Mais espaço abaixo do subtítulo */
        }

        .section {
            max-width: 1000px;
            margin-bottom: 70px; /* Aumentei o espaçamento entre as seções */
        }

        .section h3 {
            font-size: 2rem; /* Aumentei o tamanho dos títulos das seções */
            font-weight: 700;
            color: #000000;
            margin-bottom: 20px;
        }

        .section p, .section ul {
            font-size: 1.1rem; /* Aumentei o tamanho do texto das seções */
            color: #ffffff;
            margin: 0;
        }

        .section ul {
            list-style: none;
            padding: 0;
        }

        .section ul li::before {
            content: "• ";
            color: #ffffff; /* Pontos em branco */
            font-weight: bold;
        }

        .row {
            display: flex;
            justify-content: space-around;
            width: 100%;
            max-width: 1000px;
            text-align: left;
            gap: 30px; /* Espaço entre as colunas */
        }

        .col-md-6 {
            flex: 0 0 48%; /* Mais espaço entre as colunas */
        }

        footer {
            background-color: #007bff;
            color: #ffffff;
            text-align: center;
            padding: 15px;
            font-size: 1rem;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
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
                <a class="nav-link active" href="about.jsp">Sobre nós</a>
            </li>
        </ul>
    </div>
</nav>

<div class="content-wrapper">
    <div>
        <h1>Sobre a Fintech</h1>
        <p class="lead">Ajudando você a alcançar seus objetivos financeiros com segurança e facilidade.</p>
    </div>

    <div class="row section">
        <div class="col-md-6">
            <h3>Nossa Missão</h3>
            <p>
                Fornecer uma plataforma que simplifique o gerenciamento financeiro, permitindo que nossos usuários
                estabeleçam e alcancem metas, acompanhem seus investimentos e despesas, e mantenham o controle
                sobre suas finanças.
            </p>
        </div>
        <div class="col-md-6">
            <h3>Funcionalidades</h3>
            <ul>
                <li>Gestão de contas e transações</li>
                <li>Definição de metas financeiras</li>
                <li>Controle de despesas e receitas</li>
                <li>Monitoramento de investimentos</li>
                <li>Suporte para tomada de decisões financeiras</li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h3>Desenvolvimento</h3>
        <p>Desenvolvido por Matheus Silva de Souza, aluno de Análise e Desenvolvimento de Sistemas na FIAP.</p>
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
