package br.com.fiap.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String url = httpRequest.getRequestURI();

        boolean logado = (session != null && session.getAttribute("usuarioLogado") != null);
        boolean loginRequest = url.endsWith("login")
                || url.endsWith("cadastro")
                || url.endsWith(".jsp")
                || url.endsWith("/logout")
                || url.endsWith(".svg"); // Adiciona a extensão de arquivos permitidos

        if (logado || loginRequest) {
            // Desativa o cache para garantir que a página não seja acessada após o logout
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            httpResponse.setDateHeader("Expires", 0); // Proxies.
            httpResponse.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate, max-age=0");

            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {}
}
