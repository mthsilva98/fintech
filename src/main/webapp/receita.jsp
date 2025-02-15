<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Verifica se o usuário está logado --%>
<%
    if (session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%
    if (request.getAttribute("receitas") == null) {
        response.sendRedirect("receita?acao=listar");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciamento de Receitas - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #007bff;
            color: #ffffff;
            font-family: Arial, sans-serif;
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
        }

        .navbar-brand, .nav-link {
            color: #007bff !important;
            font-weight: bold;
        }

        .container {
            max-width: 800px;
            margin-top: 20px;
            margin-bottom: 20px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            color: #007bff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }

        h2 {
            font-size: 1.5rem;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #007bff;
        }

        h2 img {
            width: 24px;
            height: 24px;
            margin-right: 10px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            font-weight: bold;
            margin-top: 10px;
        }

        .btn-warning, .btn-danger {
            font-weight: bold;
            border-radius: 5px;
            padding: 5px 10px;
        }

        .card {
            background-color: #f8f9fa;
            color: #007bff;
            border-radius: 8px;
            padding: 15px;
            border: none;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .table-container {
            max-height: 300px; /* Altura máxima para permitir rolagem */
            overflow-y: auto;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 15px;
        }

        table {
            width: 100%;
            background-color: #ffffff;
            color: #007bff;
            border-radius: 8px;
            border-collapse: collapse;
        }

        table th {
            background-color: #007bff;
            color: #ffffff;
            padding: 10px;
            position: sticky;
            top: 0;
            z-index: 1;
        }

        table th, table td {
            text-align: center;
            padding: 10px;
            font-size: 0.9rem;
            white-space: nowrap;
        }

        footer {
            background-color: #f8f9fa;
            color: #212529;
            padding: 10px;
            text-align: center;
            width: 100%;
            position: relative;
            bottom: 0;
            margin-top: auto;
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
    <h2>
        <img src="${pageContext.request.contextPath}/image/Receita.svg" alt="Ícone de Receita">
        Gerenciamento de Receitas
    </h2>

    <!-- Card para adicionar nova receita -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Adicionar Nova Receita</h5>
            <form action="receita" method="post">
                <input type="hidden" name="acao" value="criar">
                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Digite a descrição da receita" required>
                </div>
                <div class="form-group">
                    <label for="valor">Valor</label>
                    <input type="number" step="0.01" class="form-control" id="valor" name="valor" placeholder="Digite o valor da receita" required>
                </div>
                <div class="form-group">
                    <label for="data">Data</label>
                    <input type="date" class="form-control" id="data" name="data" required>
                </div>
                <button type="submit" class="btn btn-primary">Adicionar Receita</button>
            </form>
        </div>
    </div>

    <!-- Tabela de receitas -->
    <h3>Suas Receitas</h3>
    <c:if test="${empty receitas}">
        <p class="text-center">Nenhuma receita cadastrada.</p>
    </c:if>
    <c:if test="${not empty receitas}">
        <div class="table-container">
            <table class="table table-striped mt-3">
                <thead>
                <tr>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="receita" items="${receitas}">
                    <tr>
                        <form action="receita" method="post">
                            <input type="hidden" name="acao" value="atualizar">
                            <input type="hidden" name="id" value="${receita.id}">
                            <td><input type="text" class="form-control" name="descricao" value="${receita.descricao}" required></td>
                            <td><input type="number" step="0.01" class="form-control" name="valor" value="${receita.valor}" required></td>
                            <td><input type="date" class="form-control" name="data" value="${fn:substring(receita.data, 0, 10)}" required></td>
                            <td>
                                <button type="submit" class="btn btn-warning btn-sm">Atualizar</button>
                                <button type="submit" formaction="receita?acao=deletar" formmethod="post" class="btn btn-danger btn-sm">Deletar</button>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>

<footer>
    <p>&copy; 2024 Fintech. Todos os direitos reservados.</p>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
