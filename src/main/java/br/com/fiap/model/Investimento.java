package br.com.fiap.model;

import java.util.Date;

public class Investimento {
    private int id;
    private int idUsuario;
    private String descricao;
    private double valor;
    private Date dataAplicacao;
    private Date dataResgate;


    public Investimento() {}


    public Investimento(int id, int idUsuario, String descricao, double valor, Date dataAplicacao, Date dataResgate) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valor = valor;
        this.dataAplicacao = dataAplicacao;
        this.dataResgate = dataResgate;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Date getDataResgate() {
        return dataResgate;
    }

    public void setDataResgate(Date dataResgate) {
        this.dataResgate = dataResgate;
    }


    @Override
    public String toString() {
        return "Investimento{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", dataAplicacao=" + dataAplicacao +
                ", dataResgate=" + dataResgate +
                '}';
    }
}
