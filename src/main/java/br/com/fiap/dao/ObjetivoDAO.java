package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Objetivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjetivoDAO {

    // Método para inserir um novo objetivo no banco de dados
    public void inserirObjetivo(Objetivo objetivo) {
        String sql = "INSERT INTO Objetivo (IDUSUARIO, DESCRICAO, VALORMETA, VALORATUAL) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, objetivo.getIdUsuario());
            stmt.setString(2, objetivo.getDescricao());
            stmt.setDouble(3, objetivo.getValorMeta());
            stmt.setDouble(4, objetivo.getValorAtual()); // Agora capturando o VALORATUAL

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Objetivo inserido com sucesso. Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            System.out.println("Erro ao inserir objetivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para buscar um objetivo pelo ID
    public Objetivo buscarObjetivoPorId(int id) {
        String sql = "SELECT * FROM Objetivo WHERE ID = ?";
        Objetivo objetivo = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                objetivo = new Objetivo(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALORMETA"),
                        rs.getDouble("VALORATUAL") // Capturando o VALORATUAL
                );
                System.out.println("Objetivo encontrado com ID: " + id);
            } else {
                System.out.println("Nenhum objetivo encontrado com ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar objetivo por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return objetivo;
    }

    // Método para listar todos os objetivos de um usuário
    public List<Objetivo> listarObjetivosPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Objetivo WHERE IDUSUARIO = ?";
        List<Objetivo> objetivos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Objetivo objetivo = new Objetivo(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALORMETA"),
                        rs.getDouble("VALORATUAL") // Capturando o VALORATUAL
                );
                objetivos.add(objetivo);
            }
            System.out.println("Objetivos listados para o usuário: " + objetivos.size());

        } catch (SQLException e) {
            System.out.println("Erro ao listar objetivos por usuário: " + e.getMessage());
            e.printStackTrace();
        }

        return objetivos;
    }

    // Método para atualizar o valor da meta e o valor atual de um objetivo
    public void atualizarValoresObjetivo(int id, double novoValorMeta, double novoValorAtual) {
        String sql = "UPDATE Objetivo SET VALORMETA = ?, VALORATUAL = ? WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novoValorMeta); // Atualiza o valor da meta
            stmt.setDouble(2, novoValorAtual); // Atualiza o valor atual
            stmt.setInt(3, id); // ID do objetivo a ser atualizado

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Objetivo atualizado. Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar valores do objetivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para deletar um objetivo pelo ID
    public void deletarObjetivo(int id) {
        String sql = "DELETE FROM Objetivo WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Objetivo deletado. Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            System.out.println("Erro ao deletar objetivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para contar todas as metas ativas de um usuário
    public int contarMetasAtivas(int idUsuario) {
        String sql = "SELECT COUNT(*) AS total FROM Objetivo WHERE IDUSUARIO = ?";
        int totalMetasAtivas = 0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalMetasAtivas = rs.getInt("total");
            }

            System.out.println("Total de metas ativas no DAO: " + totalMetasAtivas); // Log para verificar o valor no DAO
        } catch (SQLException e) {
            System.out.println("Erro ao contar metas ativas: " + e.getMessage());
            e.printStackTrace();
        }

        return totalMetasAtivas;
    }
}
