package br.com.fiap.controller;

import br.com.fiap.dao.ObjetivoDAO;
import br.com.fiap.model.Objetivo;
import br.com.fiap.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ObjetivoServlet extends HttpServlet {

    private ObjetivoDAO objetivoDAO = new ObjetivoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        System.out.println("Ação recebida no doPost: " + acao);

        switch (acao) {
            case "criar":
                criarObjetivo(request, response);
                break;
            case "atualizar":
                atualizarObjetivo(request, response);
                break;
            case "deletar":
                deletarObjetivo(request, response);
                break;
            default:
                System.out.println("Ação não reconhecida no doPost.");
                response.sendRedirect("home.jsp");
                break;
        }
    }

    private void criarObjetivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            try {
                int idUsuario = usuarioLogado.getId();
                String descricao = request.getParameter("descricao");
                double valorMeta = Double.parseDouble(request.getParameter("valorMeta"));
                double valorAtual = Double.parseDouble(request.getParameter("valorAtual")); // Campo para valor atual


                Objetivo objetivo = new Objetivo(0, idUsuario, descricao, valorMeta, valorAtual);
                objetivoDAO.inserirObjetivo(objetivo);

                // Redirecionar para a listagem de objetivos
                response.sendRedirect("objetivo?acao=listar");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void atualizarObjetivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String descricao = request.getParameter("descricao");
            double valorMeta = Double.parseDouble(request.getParameter("valorMeta"));
            double valorAtual = Double.parseDouble(request.getParameter("valorAtual")); // Atualiza o valor atual

            // Atualizar os valores no DAO
            objetivoDAO.atualizarValoresObjetivo(id, valorMeta, valorAtual); // Atualiza tanto o valor da meta quanto o valor atual

            // Redirecionar para a listagem de objetivos
            response.sendRedirect("objetivo?acao=listar");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void deletarObjetivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            objetivoDAO.deletarObjetivo(id);

            // Redirecionar para a listagem de objetivos
            response.sendRedirect("objetivo?acao=listar");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if ("listar".equals(acao)) {
            listarObjetivos(request, response);
        } else {
            response.sendRedirect("home.jsp");
        }
    }

    private void listarObjetivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int idUsuario = usuarioLogado.getId();
            List<Objetivo> objetivos = objetivoDAO.listarObjetivosPorUsuario(idUsuario);

            int metasAtivas = objetivoDAO.contarMetasAtivas(idUsuario);

            System.out.println("Total de metas ativas: " + metasAtivas);
            request.setAttribute("objetivos", objetivos);
            request.setAttribute("metasAtivas", metasAtivas);

            // Verifica se é para redirecionar para dash.jsp ou objetivo.jsp
            String paginaDestino = request.getParameter("pagina");
            if ("dash".equals(paginaDestino)) {
                request.getRequestDispatcher("dash.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("objetivo.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
