package br.com.fiap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalida a sessão
        HttpSession session = request.getSession(false); // Evita criar uma nova sessão se não existir
        if (session != null) {
            session.invalidate();
        }

        // Configura os cabeçalhos de cache no logout
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        // Redireciona para a página de login com um timestamp para evitar cache
        long timestamp = System.currentTimeMillis();
        response.sendRedirect(request.getContextPath() + "/login.jsp?ts=" + timestamp);
    }
}
