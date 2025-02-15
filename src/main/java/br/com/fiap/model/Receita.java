package br.com.fiap.model;

import java.util.Date;

public class Receita {
    private int id;
    private int idUsuario;
    private String descricao;
    private double valor;
    private Date data;


    public Receita() {}


    public Receita(int id, int idUsuario, String descricao, double valor, Date data) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}
