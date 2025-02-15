package br.com.fiap.model;

public class Objetivo {
    private int id;
    private int idUsuario;
    private String descricao;
    private double valorMeta;
    private double valorAtual; // Campo para armazenar o valor atual aplicado à meta


    public Objetivo() {}


    public Objetivo(int id, int idUsuario, String descricao, double valorMeta) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valorMeta = valorMeta;
        this.valorAtual = 0; // Valor padrão
    }


    public Objetivo(int id, int idUsuario, String descricao, double valorMeta, double valorAtual) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valorMeta = valorMeta;
        this.valorAtual = valorAtual;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }


    public double getProgresso() {
        if (valorMeta > 0) {
            return (valorAtual / valorMeta) * 100;
        } else {
            return 0;
        }
    }


    @Override
    public String toString() {
        return "Objetivo{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                ", valorMeta=" + valorMeta +
                ", valorAtual=" + valorAtual +
                '}';
    }
}
