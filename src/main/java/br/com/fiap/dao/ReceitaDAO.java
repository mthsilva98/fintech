package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Receita;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceitaDAO {

    private static final Logger LOGGER = Logger.getLogger(ReceitaDAO.class.getName());


    public void inserirReceita(Receita receita) {
        String sql = "INSERT INTO Receita (IDUSUARIO, DESCRICAO, VALOR, DATA) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, receita.getIdUsuario());
            stmt.setString(2, receita.getDescricao());
            stmt.setDouble(3, receita.getValor());
            stmt.setDate(4, new Date(receita.getData().getTime()));

            int rowsInserted = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Receita inserida com sucesso. Linhas inseridas: {0}", rowsInserted);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir receita: {0}", e.getMessage());
            e.printStackTrace();
        }
    }


    public Receita buscarReceitaPorId(int id) {
        String sql = "SELECT * FROM Receita WHERE ID = ?";
        Receita receita = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                receita = new Receita(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DATA")
                );
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar receita por ID: {0}", e.getMessage());
            e.printStackTrace();
        }

        return receita;
    }

    // Listar receitas por usuário
    public List<Receita> listarReceitasPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Receita WHERE IDUSUARIO = ?";
        List<Receita> receitas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Receita receita = new Receita(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DATA")
                );
                receitas.add(receita);
            }
            LOGGER.log(Level.INFO, "Receitas encontradas para o usuário {0}: {1}", new Object[]{idUsuario, receitas.size()});

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar receitas: {0}", e.getMessage());
            e.printStackTrace();
        }

        return receitas;
    }


    public void atualizarReceita(int id, String descricao, double valor, Date data) {
        String sql = "UPDATE Receita SET DESCRICAO = ?, VALOR = ?, DATA = ? WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao);
            stmt.setDouble(2, valor);
            stmt.setDate(3, data);
            stmt.setInt(4, id);

            int rowsUpdated = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Receita atualizada com sucesso. Linhas atualizadas: {0}", rowsUpdated);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar receita: {0}", e.getMessage());
            e.printStackTrace();
        }
    }


    public void deletarReceita(int id) {
        String sql = "DELETE FROM Receita WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Receita deletada com sucesso. Linhas deletadas: {0}", rowsDeleted);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar receita: {0}", e.getMessage());
            e.printStackTrace();
        }
    }


    public double calcularTotalReceitas(int idUsuario) {
        String sql = "SELECT SUM(VALOR) FROM Receita WHERE IDUSUARIO = ?";
        double totalReceitas = 0.0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalReceitas = rs.getDouble(1);
            }
            LOGGER.log(Level.INFO, "Total de receitas calculado para o usuário {0}: {1}", new Object[]{idUsuario, totalReceitas});

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao calcular total de receitas: {0}", e.getMessage());
            e.printStackTrace();
        }

        return totalReceitas;
    }
}
