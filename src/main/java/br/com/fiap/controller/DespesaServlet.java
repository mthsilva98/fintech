package br.com.fiap.controller;

import br.com.fiap.dao.DespesaDAO;
import br.com.fiap.model.Despesa;
import br.com.fiap.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class DespesaServlet extends HttpServlet {

    private DespesaDAO despesaDAO = new DespesaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        System.out.println("Ação recebida no doPost: " + acao);

        switch (acao) {
            case "criar":
                criarDespesa(request, response);
                break;
            case "atualizar":
                atualizarDespesa(request, response);
                break;
            case "deletar":
                deletarDespesa(request, response);
                break;
            default:
                listarDespesas(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        System.out.println("Ação recebida no doGet: " + acao);

        if ("listar".equals(acao)) {
            listarDespesas(request, response);
        } else {
            response.sendRedirect("home.jsp");
        }
    }

    private void criarDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            try {
                int idUsuario = usuarioLogado.getId();
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                String data = request.getParameter("data");
                String categoria = request.getParameter("categoria");

                // Validação dos dados da despesa
                if (descricao.isEmpty() || categoria.isEmpty() || valor <= 0 || data == null) {
                    response.sendRedirect("despesa.jsp?erro=Dados inválidos");
                    return;
                }

                Despesa despesa = new Despesa(0, idUsuario, descricao, valor, java.sql.Date.valueOf(data), categoria);
                despesaDAO.inserirDespesa(despesa);

                // Após criar a despesa, redirecione para listar
                response.sendRedirect("despesa?acao=listar");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void atualizarDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            String data = request.getParameter("data");
            String categoria = request.getParameter("categoria");

            Despesa despesa = despesaDAO.buscarDespesaPorId(id);
            if (despesa != null) {
                despesa.setDescricao(descricao);
                despesa.setValor(valor);
                despesa.setData(java.sql.Date.valueOf(data));
                despesa.setCategoria(categoria);
                despesaDAO.atualizarDespesa(despesa);

                // Após atualizar a despesa, redirecione para listar
                response.sendRedirect("despesa?acao=listar");
            } else {
                response.sendRedirect("despesa.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void deletarDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            despesaDAO.deletarDespesa(id);

            // Após deletar a despesa, redirecione para listar
            response.sendRedirect("despesa?acao=listar");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void listarDespesas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            try {
                int idUsuario = usuarioLogado.getId();
                List<Despesa> despesas = despesaDAO.listarDespesasPorUsuario(idUsuario);

                System.out.println("Despesas carregadas para o usuário " + idUsuario + ": " + despesas.size());

                // Definindo o atributo despesas para ser acessado no JSP
                request.setAttribute("despesas", despesas);
                request.getRequestDispatcher("despesa.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
