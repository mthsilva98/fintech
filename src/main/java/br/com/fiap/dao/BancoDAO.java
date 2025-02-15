package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDAO {

    public void inserirUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO Usuario (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Usuário encontrado: " + rs.getString("nome"));

            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuarioPorEmail(String email) {
        String sql = "DELETE FROM Usuario WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Usuário deletado com sucesso.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarSenhaUsuario(String email, String novaSenha) {
        String sql = "UPDATE Usuario SET senha = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaSenha);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Senha atualizada com sucesso.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
