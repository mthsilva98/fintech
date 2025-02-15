package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DespesaDAO {

    private static final Logger LOGGER = Logger.getLogger(DespesaDAO.class.getName());

    // Método para inserir uma nova despesa no banco de dados
    public void inserirDespesa(Despesa despesa) {
        String sql = "INSERT INTO Despesa (IDUSUARIO, DESCRICAO, VALOR, DATA, CATEGORIA) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, despesa.getIdUsuario());
            stmt.setString(2, despesa.getDescricao());
            stmt.setDouble(3, despesa.getValor());
            stmt.setDate(4, new java.sql.Date(despesa.getData().getTime()));
            stmt.setString(5, despesa.getCategoria()); // Define a categoria

            int rowsAffected = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Despesa inserida com sucesso. Linhas inseridas: {0}", rowsAffected);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir despesa: {0}", e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para buscar uma despesa por ID
    public Despesa buscarDespesaPorId(int id) {
        String sql = "SELECT * FROM Despesa WHERE ID = ?";
        Despesa despesa = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                despesa = new Despesa(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DATA"),
                        rs.getString("CATEGORIA") // Obtém a categoria
                );
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar despesa por ID: {0}", e.getMessage());
            e.printStackTrace();
        }

        return despesa;
    }

    // Método para listar todas as despesas de um usuário específico
    public List<Despesa> listarDespesasPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Despesa WHERE IDUSUARIO = ?";
        List<Despesa> despesas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Despesa despesa = new Despesa(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DATA"),
                        rs.getString("CATEGORIA") // Obtém a categoria
                );
                despesas.add(despesa);
            }

            LOGGER.log(Level.INFO, "Despesas encontradas para o usuário {0}: {1}", new Object[]{idUsuario, despesas.size()});

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar despesas: {0}", e.getMessage());
            e.printStackTrace();
        }

        return despesas;
    }

    // Método para atualizar uma despesa específica
    public void atualizarDespesa(Despesa despesa) {
        String sql = "UPDATE Despesa SET DESCRICAO = ?, VALOR = ?, DATA = ?, CATEGORIA = ? WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());
            stmt.setDate(3, new java.sql.Date(despesa.getData().getTime()));
            stmt.setString(4, despesa.getCategoria());
            stmt.setInt(5, despesa.getId());

            int rowsAffected = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Despesa atualizada com sucesso. Linhas atualizadas: {0}", rowsAffected);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar despesa: {0}", e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para deletar uma despesa específica
    public void deletarDespesa(int id) {
        String sql = "DELETE FROM Despesa WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Despesa deletada com sucesso. Linhas deletadas: {0}", rowsAffected);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar despesa: {0}", e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para calcular o total de despesas para o usuário
    public double calcularTotalDespesas(int idUsuario) {
        String sql = "SELECT SUM(VALOR) FROM Despesa WHERE IDUSUARIO = ?";
        double totalDespesas = 0.0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalDespesas = rs.getDouble(1);
            }
            LOGGER.log(Level.INFO, "Total de despesas calculado para o usuário {0}: {1}", new Object[]{idUsuario, totalDespesas});

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao calcular total de despesas: {0}", e.getMessage());
            e.printStackTrace();
        }

        return totalDespesas;
    }

    // Método para calcular o total de despesas por categoria para o usuário
    public double calcularTotalPorCategoria(int idUsuario, String categoria) {
        String sql = "SELECT SUM(VALOR) FROM Despesa WHERE IDUSUARIO = ? AND CATEGORIA = ?";
        double totalPorCategoria = 0.0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, categoria);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalPorCategoria = rs.getDouble(1);
            }
            LOGGER.log(Level.INFO, "Total de despesas na categoria '{0}' para o usuário {1}: {2}", new Object[]{categoria, idUsuario, totalPorCategoria});

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao calcular total de despesas por categoria: {0}", e.getMessage());
            e.printStackTrace();
        }

        return totalPorCategoria;
    }
}
