package br.com.fiap.controller;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.model.Conta;
import br.com.fiap.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ContaServlet extends HttpServlet {

    private ContaDAO contaDAO = new ContaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "criar":
                criarConta(request, response);
                break;
            case "atualizar":
                atualizarConta(request, response);
                break;
            case "deletar":
                deletarConta(request, response);
                break;
            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    private void criarConta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int idUsuario = usuarioLogado.getId();
            String tipo = request.getParameter("tipo");
            double saldo = Double.parseDouble(request.getParameter("saldo"));

            Conta conta = new Conta(0, idUsuario, saldo, tipo);
            contaDAO.inserirConta(conta);

            response.sendRedirect("conta?acao=listar");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void atualizarConta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");
        double saldo = Double.parseDouble(request.getParameter("saldo"));

        Conta conta = contaDAO.buscarContaPorId(id);
        if (conta != null) {
            contaDAO.atualizarConta(id, saldo, tipo);  // Atualiza saldo e tipo
            response.sendRedirect("conta?acao=listar");
        } else {
            response.sendRedirect("conta.jsp");
        }
    }

    private void deletarConta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        contaDAO.deletarConta(id);

        response.sendRedirect("conta?acao=listar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if ("listar".equals(acao)) {
            listarContas(request, response);
        } else {
            response.sendRedirect("home.jsp");
        }
    }

    private void listarContas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int idUsuario = usuarioLogado.getId();
            List<Conta> contas = contaDAO.listarContasPorUsuario(idUsuario);
            request.setAttribute("contas", contas);
            request.getRequestDispatcher("conta.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
