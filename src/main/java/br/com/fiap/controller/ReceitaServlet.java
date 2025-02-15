package br.com.fiap.controller;

import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.model.Receita;
import br.com.fiap.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceitaServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ReceitaServlet.class.getName());
    private ReceitaDAO receitaDAO = new ReceitaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        LOGGER.log(Level.INFO, "Ação recebida no doPost: {0}", acao);

        switch (acao) {
            case "criar":
                criarReceita(request, response);
                break;
            case "atualizar":
                atualizarReceita(request, response);
                break;
            case "deletar":
                deletarReceita(request, response);
                break;
            default:
                listarReceitas(request, response);
                break;
        }
    }

    private void criarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            try {
                int idUsuario = usuarioLogado.getId();
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                Date data = Date.valueOf(request.getParameter("data"));

                Receita receita = new Receita(0, idUsuario, descricao, valor, data);
                LOGGER.log(Level.INFO, "Tentando inserir receita no banco de dados: {0}", receita);
                receitaDAO.inserirReceita(receita);

                LOGGER.log(Level.INFO, "Receita inserida com sucesso.");
                response.sendRedirect("receita?acao=listar");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erro ao criar receita: {0}", e.getMessage());
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void atualizarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            Date data = Date.valueOf(request.getParameter("data"));

            LOGGER.log(Level.INFO, "Atualizando receita com ID {0}", id);
            receitaDAO.atualizarReceita(id, descricao, valor, data);

            response.sendRedirect("receita?acao=listar");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar receita: {0}", e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void deletarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            LOGGER.log(Level.INFO, "Deletando receita com ID {0}", id);
            receitaDAO.deletarReceita(id);

            response.sendRedirect("receita?acao=listar");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar receita: {0}", e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        LOGGER.log(Level.INFO, "Ação recebida no doGet: {0}", acao);

        if ("listar".equals(acao)) {
            listarReceitas(request, response);
        } else {
            response.sendRedirect("home.jsp");
        }
    }

    private void listarReceitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int idUsuario = usuarioLogado.getId();
            try {
                List<Receita> receitas = receitaDAO.listarReceitasPorUsuario(idUsuario);
                LOGGER.log(Level.INFO, "Receitas encontradas para o usuário {0}: {1}", new Object[]{idUsuario, receitas.size()});

                request.setAttribute("receitas", receitas);
                request.getRequestDispatcher("receita.jsp").forward(request, response);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erro ao listar receitas: {0}", e.getMessage());
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
