<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- Verifica se o usuário está logado --%>
<%
    if (session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%
    if (request.getAttribute("despesas") == null) {
        response.sendRedirect("despesa?acao=listar");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciamento de Despesas - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            color: #212529;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .navbar {
            background-color: #007bff;
            padding: 1rem;
            width: 100%;
            display: flex;
            justify-content: space-between;
            color: #fff;
        }

        .navbar-brand, .nav-link {
            color: #ffffff !important;
            font-weight: bold;
        }

        .container {
            max-width: 800px;
            margin-top: 30px;
            margin-bottom: 20px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            color: #212529;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 1.8rem;
            font-weight: bold;
            color: #007bff;
            display: flex;
            align-items: center;
        }

        h2 img {
            width: 28px;
            height: 28px;
            margin-right: 10px;
        }

        .form-group input,
        .form-group select {
            border-radius: 5px;
            padding: 10px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            font-weight: bold;
            margin-top: 10px;
            width: 100%;
        }

        .btn-update, .btn-delete {
            color: #ffffff;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
        }

        .btn-update {
            background-color: #17a2b8;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        .card {
            background-color: #f8f9fa;
            color: #212529;
            border-radius: 8px;
            padding: 15px;
            border: none;
            margin-bottom: 20px;
        }

        .table-container {
            max-height: 300px;
            overflow-y: auto;
            margin-top: 15px;
        }

        table {
            width: 100%;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            border-collapse: collapse;
        }

        table th {
            background-color: #007bff;
            color: #ffffff;
            padding: 12px;
        }

        table th, table td {
            text-align: center;
            padding: 12px;
            font-size: 0.9rem;
        }

        table tbody tr:nth-child(even) {
            background-color: #e9ecef;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        footer {
            background-color: #007bff;
            color: #ffffff;
            padding: 10px;
            text-align: center;
            width: 100%;
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
    <h2><img src="${pageContext.request.contextPath}/image/Despesa.svg" alt="Ícone de Despesa"> Gerenciamento de Despesas</h2>

    <!-- Card para adicionar nova despesa -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Adicionar Nova Despesa</h5>
            <form action="despesa" method="post">
                <input type="hidden" name="acao" value="criar">
                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Digite a descrição da despesa" required>
                </div>
                <div class="form-group">
                    <label for="valor">Valor</label>
                    <input type="number" step="0.01" class="form-control" id="valor" name="valor" placeholder="Digite o valor da despesa" required>
                </div>
                <div class="form-group">
                    <label for="data">Data</label>
                    <input type="date" class="form-control" id="data" name="data" required>
                </div>
                <div class="form-group">
                    <label for="categoria">Categoria</label>
                    <select class="form-control" id="categoria" name="categoria" required>
                        <option value="Alimentação">Alimentação</option>
                        <option value="Contas">Contas</option>
                        <option value="Moradia">Moradia</option>
                        <option value="Lazer">Lazer</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Adicionar Despesa</button>
            </form>
        </div>
    </div>

    <!-- Card para lista de despesas -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Suas Despesas</h5>
            <div class="table-container">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Data</th>
                        <th>Categoria</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="despesa" items="${despesas}">
                        <tr>
                            <form action="despesa" method="post">
                                <td><input type="text" class="form-control" name="descricao" value="${despesa.descricao}" required></td>
                                <td><input type="number" step="0.01" class="form-control" name="valor" value="${despesa.valor}" required></td>
                                <td><input type="date" class="form-control" name="data" value="${despesa.data}" required></td>
                                <td>
                                    <select class="form-control" name="categoria" required>
                                        <option value="Alimentação" ${despesa.categoria == 'Alimentação' ? 'selected' : ''}>Alimentação</option>
                                        <option value="Contas" ${despesa.categoria == 'Contas' ? 'selected' : ''}>Contas</option>
                                        <option value="Moradia" ${despesa.categoria == 'Moradia' ? 'selected' : ''}>Moradia</option>
                                        <option value="Lazer" ${despesa.categoria == 'Lazer' ? 'selected' : ''}>Lazer</option>
                                    </select>
                                </td>
                                <td class="action-buttons">
                                    <input type="hidden" name="acao" value="atualizar">
                                    <input type="hidden" name="id" value="${despesa.id}">
                                    <button type="submit" class="btn btn-update">Atualizar</button>
                                    <button type="submit" formaction="despesa?acao=deletar" formmethod="post" class="btn btn-delete">Deletar</button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
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
