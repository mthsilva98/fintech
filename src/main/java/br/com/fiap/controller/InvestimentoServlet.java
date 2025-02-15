package br.com.fiap.controller;

import br.com.fiap.dao.InvestimentoDAO;
import br.com.fiap.model.Investimento;
import br.com.fiap.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvestimentoServlet extends HttpServlet {

    private InvestimentoDAO investimentoDAO = new InvestimentoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        System.out.println("Ação recebida no doPost: " + acao);

        try {
            switch (acao) {
                case "criar":
                    criarInvestimento(request, response);
                    break;
                case "atualizar":
                    atualizarInvestimento(request, response);
                    break;
                case "deletar":
                    deletarInvestimento(request, response);
                    break;
                default:
                    response.sendRedirect("home.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("tipoErro", e.getClass().getName());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    private void criarInvestimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            try {
                int idUsuario = usuarioLogado.getId();
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                Date dataAplicacao = parseDate(request.getParameter("dataAplicacao"));
                Date dataResgate = parseDate(request.getParameter("dataResgate"));

                Investimento investimento = new Investimento(0, idUsuario, descricao, valor, dataAplicacao, dataResgate);
                investimentoDAO.inserirInvestimento(investimento);

                response.sendRedirect("investimento?acao=listar");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensagemErro", e.getMessage());
                request.setAttribute("tipoErro", e.getClass().getName());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void atualizarInvestimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            Date dataAplicacao = parseDate(request.getParameter("dataAplicacao"));
            Date dataResgate = parseDate(request.getParameter("dataResgate"));

            Investimento investimento = investimentoDAO.buscarInvestimentoPorId(id);
            if (investimento != null) {
                investimento.setDescricao(descricao);
                investimento.setValor(valor);
                investimento.setDataAplicacao(dataAplicacao);
                investimento.setDataResgate(dataResgate);
                investimentoDAO.atualizarValorInvestimento(id, valor);

                response.sendRedirect("investimento?acao=listar");
            } else {
                response.sendRedirect("invest.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("tipoErro", e.getClass().getName());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    private void deletarInvestimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            investimentoDAO.deletarInvestimento(id);

            response.sendRedirect("investimento?acao=listar");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("tipoErro", e.getClass().getName());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        System.out.println("Ação recebida no doGet: " + acao);

        try {
            if ("listar".equals(acao)) {
                listarInvestimentos(request, response);
            } else {
                response.sendRedirect("home.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("tipoErro", e.getClass().getName());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    private void listarInvestimentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int idUsuario = usuarioLogado.getId();
            List<Investimento> investimentos = investimentoDAO.listarInvestimentosPorUsuario(idUsuario);
            request.setAttribute("investimentos", investimentos);
            request.getRequestDispatcher("invest.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
