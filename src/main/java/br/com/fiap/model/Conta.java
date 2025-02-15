package br.com.fiap.model;

public class Conta {
    private int id;
    private int idUsuario;
    private double saldo;
    private String tipo;


    public Conta() {}


    public Conta(int id, int idUsuario, double saldo, String tipo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", saldo=" + saldo +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
