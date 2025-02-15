package br.com.fiap.controller;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.dao.DespesaDAO;
import br.com.fiap.dao.ObjetivoDAO;
import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.model.Objetivo;
import br.com.fiap.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class DashboardServlet extends HttpServlet {

    private ContaDAO contaDAO = new ContaDAO();
    private ReceitaDAO receitaDAO = new ReceitaDAO();
    private DespesaDAO despesaDAO = new DespesaDAO();
    private ObjetivoDAO objetivoDAO = new ObjetivoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int idUsuario = usuarioLogado.getId();
            System.out.println("Usuário logado com ID: " + idUsuario);

            // Obtem os valores do banco de dados
            double saldoTotal = contaDAO.calcularSaldoTotal(idUsuario);
            double totalReceitas = receitaDAO.calcularTotalReceitas(idUsuario);
            double totalDespesas = despesaDAO.calcularTotalDespesas(idUsuario);
            int metasAtivas = objetivoDAO.contarMetasAtivas(idUsuario);

            // Obtem o total por categoria para o gráfico de despesas
            double totalAlimentacao = despesaDAO.calcularTotalPorCategoria(idUsuario, "Alimentação");
            double totalContas = despesaDAO.calcularTotalPorCategoria(idUsuario, "Contas");
            double totalMoradia = despesaDAO.calcularTotalPorCategoria(idUsuario, "Moradia");
            double totalLazer = despesaDAO.calcularTotalPorCategoria(idUsuario, "Lazer");

            // Obtem os objetivos e calcule o progresso de cada um
            List<Objetivo> objetivos = objetivoDAO.listarObjetivosPorUsuario(idUsuario);

            // Passar os valores para o JSP
            request.setAttribute("saldoTotal", saldoTotal);
            request.setAttribute("totalReceitas", totalReceitas);
            request.setAttribute("totalDespesas", totalDespesas);
            request.setAttribute("metasAtivas", metasAtivas);
            request.setAttribute("objetivos", objetivos);

            // Totais por categoria para o gráfico de despesas
            request.setAttribute("totalAlimentacao", totalAlimentacao);
            request.setAttribute("totalContas", totalContas);
            request.setAttribute("totalMoradia", totalMoradia);
            request.setAttribute("totalLazer", totalLazer);

            request.getRequestDispatcher("dash.jsp").forward(request, response);
        } else {
            System.out.println("Usuário não logado, redirecionando para login.jsp");
            response.sendRedirect("login.jsp");
        }
    }
}
