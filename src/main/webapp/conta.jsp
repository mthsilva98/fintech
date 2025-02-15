<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (request.getAttribute("contas") == null) {
        response.sendRedirect("conta?acao=listar");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciamento de Contas - Fintech</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #007bff;
            color: #ffffff;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            margin: 0;
            overflow-y: hidden;
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
            width: 100%;
            max-width: 600px;
            margin-top: 80px; /* Espaço para a barra de navegação fixa */
            margin-bottom: 80px; /* Espaço para o rodapé */
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            color: #007bff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            overflow-y: auto;
            max-height: calc(100vh - 160px); /* Altura máxima para a barra de rolagem */
        }

        h2 {
            font-size: 1.2rem;
            font-weight: bold;
            display: flex;
            align-items: center;
            color: #007bff;
            margin-bottom: 15px;
            justify-content: center;
        }

        h2 img {
            width: 24px;
            height: 24px;
            margin-right: 10px;
        }

        .form-control, .btn {
            border-radius: 8px;
            box-shadow: none;
        }

        .form-inline .form-control {
            width: auto;
            margin-right: 10px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 5px;
            padding: 8px 12px;
        }

        .btn-update {
            background-color: #17a2b8;
            color: #ffffff;
            font-weight: bold;
            border: none;
            padding: 5px 10px;
            margin-right: 5px;
        }

        .btn-delete {
            background-color: #dc3545;
            color: #ffffff;
            font-weight: bold;
            border: none;
            padding: 5px 10px;
        }

        .card {
            background-color: #f8f9fa;
            color: #007bff;
            border-radius: 8px;
            padding: 15px;
            border: none;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-title {
            font-size: 1rem;
            font-weight: bold;
            color: #007bff;
        }

        table {
            width: 100%;
            background-color: #ffffff;
            color: #007bff;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 15px;
            border-collapse: collapse;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        table th, table td {
            text-align: center;
            padding: 8px;
            font-size: 0.9rem;
            border: 1px solid #e0e0e0;
        }

        table thead {
            background-color: #007bff;
            color: #ffffff;
        }

        table tbody tr {
            background-color: #f9f9f9;
        }

        table tbody tr:nth-child(even) {
            background-color: #e9ecef;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
        }

        footer {
            background-color: #f8f9fa;
            color: #212529;
            padding: 10px;
            text-align: center;
            width: 100%;
            position: fixed;
            bottom: 0;
            z-index: 1000;
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
    <h2><img src="${pageContext.request.contextPath}/image/Conta.svg" alt="Ícone de Conta"> Gerenciamento de Contas</h2>

    <!-- Formulário para adicionar uma nova conta -->
    <div class="card mt-4">
        <div class="card-body">
            <h5 class="card-title">Adicionar Nova Conta</h5>
            <form action="conta" method="post" class="form-inline">
                <input type="hidden" name="acao" value="criar">
                <select name="tipo" class="form-control mr-2" required>
                    <option value="Corrente">Conta Corrente</option>
                    <option value="Poupança">Poupança</option>
                </select>
                <input type="number" step="0.01" class="form-control mr-2" name="saldo" placeholder="Saldo inicial" required>
                <button type="submit" class="btn btn-primary"><span>&#43;</span> Adicionar Conta</button>
            </form>
        </div>
    </div>

    <!-- Listagem de contas existentes -->
    <div class="mt-5">
        <h5>Suas Contas</h5>
        <table class="table">
            <thead>
            <tr>
                <th>Tipo de conta</th>
                <th>Saldo</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="conta" items="${contas}">
                <tr>
                    <form action="conta" method="post">
                        <td>
                            <select name="tipo" class="form-control">
                                <option value="Corrente" ${conta.tipo == 'Corrente' ? 'selected' : ''}>Corrente</option>
                                <option value="Poupança" ${conta.tipo == 'Poupança' ? 'selected' : ''}>Poupança</option>
                            </select>
                        </td>
                        <td><input type="number" step="0.01" class="form-control" name="saldo" value="${conta.saldo}" placeholder="Saldo"></td>
                        <td class="action-buttons">
                            <input type="hidden" name="acao" value="atualizar">
                            <input type="hidden" name="id" value="${conta.id}">
                            <button type="submit" class="btn btn-update">Atualizar</button>
                            <button type="submit" formaction="conta?acao=deletar" formmethod="post" class="btn btn-delete">Deletar</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
