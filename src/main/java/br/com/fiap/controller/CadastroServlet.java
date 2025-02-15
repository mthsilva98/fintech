package br.com.fiap.controller;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CadastroServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros do formulário de cadastro
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Verifica se os campos obrigatórios foram preenchidos
        if (nome == null || email == null || senha == null || nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            request.setAttribute("mensagemErro", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("cadastra-usuario.jsp").forward(request, response);
            return;
        }

        // Cria um novo usuário e insere no banco de dados
        Usuario usuario = new Usuario(0, nome, email, senha);
        usuarioDAO.inserirUsuario(usuario);

        // Redireciona para a página de login após o cadastro
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona para a página de cadastro caso o método GET seja chamado
        request.getRequestDispatcher("cadastra-usuario.jsp").forward(request, response);
    }
}
