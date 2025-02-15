package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public void inserirConta(Conta conta) {
        String sql = "INSERT INTO Conta (idusuario, saldo, tipo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, conta.getIdUsuario());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setString(3, conta.getTipo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conta buscarContaPorId(int id) {
        String sql = "SELECT * FROM Conta WHERE id = ?";
        Conta conta = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                conta = new Conta(
                        rs.getInt("id"),
                        rs.getInt("idusuario"),
                        rs.getDouble("saldo"),
                        rs.getString("tipo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conta;
    }

    public List<Conta> listarContasPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Conta WHERE idusuario = ?";
        List<Conta> contas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Conta conta = new Conta(
                        rs.getInt("id"),
                        rs.getInt("idusuario"),
                        rs.getDouble("saldo"),
                        rs.getString("tipo")
                );
                contas.add(conta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contas;
    }

    public void atualizarConta(int id, double novoSaldo, String novoTipo) {
        String sql = "UPDATE Conta SET saldo = ?, tipo = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novoSaldo);
            stmt.setString(2, novoTipo);
            stmt.setInt(3, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarConta(int id) {
        String sql = "DELETE FROM Conta WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calcularSaldoTotal(int idUsuario) {
        String sql = "SELECT SUM(saldo) FROM Conta WHERE idusuario = ?";
        double saldoTotal = 0.0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                saldoTotal = rs.getDouble(1);
                System.out.println("Saldo Total obtido: " + saldoTotal); // Adicione este log
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldoTotal;
    }


    public double calcularTotalReceitas(int idUsuario) {
        String sql = "SELECT SUM(saldo) FROM Conta WHERE idusuario = ? AND tipo = 'Receita'";
        double totalReceitas = 0.0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalReceitas = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalReceitas;
    }

    public double calcularTotalDespesas(int idUsuario) {
        String sql = "SELECT SUM(saldo) FROM Conta WHERE idusuario = ? AND tipo = 'Despesa'";
        double totalDespesas = 0.0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalDespesas = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalDespesas;
    }
}
