package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Investimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDAO {

    public void inserirInvestimento(Investimento investimento) {
        String sql = "INSERT INTO Investimento (IDUSUARIO, DESCRICAO, VALOR, DATAAPLICACAO, DATARESGATE) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, investimento.getIdUsuario());
            stmt.setString(2, investimento.getDescricao());
            stmt.setDouble(3, investimento.getValor());
            stmt.setDate(4, new java.sql.Date(investimento.getDataAplicacao().getTime()));
            stmt.setDate(5, new java.sql.Date(investimento.getDataResgate().getTime()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Investimento buscarInvestimentoPorId(int id) {
        String sql = "SELECT * FROM Investimento WHERE ID = ?";
        Investimento investimento = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                investimento = new Investimento(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DATAAPLICACAO"),
                        rs.getDate("DATARESGATE")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return investimento;
    }

    public List<Investimento> listarInvestimentosPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Investimento WHERE IDUSUARIO = ?";
        List<Investimento> investimentos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Investimento investimento = new Investimento(
                        rs.getInt("ID"),
                        rs.getInt("IDUSUARIO"),
                        rs.getString("DESCRICAO"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DATAAPLICACAO"),
                        rs.getDate("DATARESGATE")
                );
                investimentos.add(investimento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return investimentos;
    }

    public void atualizarValorInvestimento(int id, double novoValor) {
        String sql = "UPDATE Investimento SET VALOR = ? WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novoValor);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarInvestimento(int id) {
        String sql = "DELETE FROM Investimento WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
