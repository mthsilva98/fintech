<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>Dashboard - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #007bff;
            color: #ffffff;
            font-family: Arial, sans-serif;
            margin: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .navbar {
            background-color: #ffffff;
            padding: 1rem;
            width: 100%;
            display: flex;
            justify-content: space-between;
            position: fixed;
            top: 0;
            z-index: 1000;
        }
        .navbar-brand, .nav-link {
            color: #007bff !important;
            font-weight: bold;
        }
        .container {
            max-width: 1200px;
            padding-top: 100px;
            padding-bottom: 20px;
            flex: 1;
        }
        .card {
            background-color: #fff;
            color: #333;
            border: none;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px;
        }
        .icon img {
            width: 40px;
            height: 40px;
        }
        .progress-background {
            background-color: #e0e0e0;
            border-radius: 4px;
            height: 8px;
        }
        .progress-bar-custom {
            background-color: #007bff;
            height: 8px;
            border-radius: 4px;
        }
        footer {
            background-color: #f8f9fa;
            color: #212529;
            padding: 10px;
            text-align: center;
            width: 100%;
        }
        #despesasChart {
            max-width: 100%;
            max-height: 400px;
            margin: 0 auto;
        }
        .side-labels {
            text-align: center;
            margin-top: 20px;
        }
        .side-labels p {
            font-weight: bold;
            margin: 5px 0;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="home.jsp">Fintech</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="home.jsp">Início</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sair</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row text-center">
        <!-- Card de Saldo Total -->
        <div class="col-md-3 mb-4">
            <div class="card">
                <div class="d-flex align-items-center justify-content-center">
                    <div class="icon mr-3">
                        <img src="${pageContext.request.contextPath}/image/Conta.svg" alt="Saldo Total">
                    </div>
                    <div>
                        <h6>Saldo total</h6>
                        <h4>R$ <c:out value="${saldoTotal}"/></h4>
                    </div>
                </div>
            </div>
        </div>
        <!-- Card de Receitas -->
        <div class="col-md-3 mb-4">
            <div class="card">
                <div class="d-flex align-items-center justify-content-center">
                    <div class="icon mr-3">
                        <img src="${pageContext.request.contextPath}/image/Receita.svg" alt="Receitas">
                    </div>
                    <div>
                        <h6>Receitas</h6>
                        <h4>R$ <c:out value="${totalReceitas}"/></h4>
                    </div>
                </div>
            </div>
        </div>
        <!-- Card de Despesas -->
        <div class="col-md-3 mb-4">
            <div class="card">
                <div class="d-flex align-items-center justify-content-center">
                    <div class="icon mr-3">
                        <img src="${pageContext.request.contextPath}/image/Despesa.svg" alt="Despesas">
                    </div>
                    <div>
                        <h6>Despesas</h6>
                        <h4>R$ <c:out value="${totalDespesas}"/></h4>
                    </div>
                </div>
            </div>
        </div>
        <!-- Card de Metas Ativas -->
        <div class="col-md-3 mb-4">
            <div class="card">
                <div class="d-flex align-items-center justify-content-center">
                    <div class="icon mr-3">
                        <img src="${pageContext.request.contextPath}/image/Meta.svg" alt="Metas Ativas">
                    </div>
                    <div>
                        <h6>Metas Ativas</h6>
                        <h4><c:out value="${metasAtivas}"/></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <!-- Gráfico de Distribuição de Despesas -->
        <div class="col-md-6 mb-4">
            <div class="card">
                <h5>Distribuição de Despesas</h5>
                <canvas id="despesasChart"></canvas>
                <div class="side-labels">
                    <p style="color: #FFC107;">Alimentação: <span id="percentAlimentacao"></span>%</p>
                    <p style="color: #007BFF;">Contas: <span id="percentContas"></span>%</p>
                    <p style="color: #4CAF50;">Moradia: <span id="percentMoradia"></span>%</p>
                    <p style="color: #F44336;">Lazer: <span id="percentLazer"></span>%</p>
                </div>
            </div>
        </div>


        <div class="col-md-6 mb-4">
            <div class="card">
                <h5>Metas Financeiras</h5>
                <c:forEach var="objetivo" items="${objetivos}">
                    <p>${objetivo.descricao} <span class="float-right"><fmt:formatNumber value="${objetivo.progresso}" maxFractionDigits="2"/>%</span></p>
                    <div class="progress progress-background mb-3">
                        <div class="progress-bar-custom" role="progressbar" style="width: ${objetivo.progresso}%;"></div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<footer>
    <p>&copy; 2024 Fintech. Todos os direitos reservados.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0/dist/chartjs-plugin-datalabels.min.js"></script>
<script>
    // Variáveis de despesas reais
    var totalAlimentacao = ${totalAlimentacao};
    var totalContas = ${totalContas};
    var totalMoradia = ${totalMoradia};
    var totalLazer = ${totalLazer};

    // Calcular porcentagens
    var totalDespesas = totalAlimentacao + totalContas + totalMoradia + totalLazer;
    var porcentagens = [
        ((totalAlimentacao / totalDespesas) * 100).toFixed(0),
        ((totalContas / totalDespesas) * 100).toFixed(0),
        ((totalMoradia / totalDespesas) * 100).toFixed(0),
        ((totalLazer / totalDespesas) * 100).toFixed(0)
    ];

    document.getElementById('percentAlimentacao').textContent = porcentagens[0];
    document.getElementById('percentContas').textContent = porcentagens[1];
    document.getElementById('percentMoradia').textContent = porcentagens[2];
    document.getElementById('percentLazer').textContent = porcentagens[3];

    // Criar gráfico de pizza
    var ctx = document.getElementById('despesasChart').getContext('2d');
    var despesasChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Alimentação', 'Contas', 'Moradia', 'Lazer'],
            datasets: [{
                data: porcentagens,
                backgroundColor: ['#FFC107', '#007BFF', '#4CAF50', '#F44336'],
            }]
        },
        options: {
            plugins: {
                datalabels: {
                    color: '#ffffff',
                    display: true,
                    formatter: (value) => value + "%", // Formatação com uma casa decimal
                    font: {
                        weight: 'bold',
                        size: 14
                    }
                }
            },
            responsive: true,
            maintainAspectRatio: true,
            legend: {
                display: true,
                position: 'top'
            }
        }
    });
</script>
</body>
</html>
