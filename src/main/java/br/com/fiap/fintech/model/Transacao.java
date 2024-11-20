package br.com.fiap.fintech.model;

import java.time.LocalDate;

public abstract class Transacao {
    private int id;
    private int codigoTransacao;
    private double valorTransacao;
    private String descricao;
    private LocalDate data ;

    public Transacao(int id,int codigoTransacao,double valorTransacao, LocalDate data){
        this.valorTransacao=valorTransacao;
        this.id=id;
        this.codigoTransacao=codigoTransacao;
        this.data=data;

    }

    public Transacao(int id,int codigoTransacao,double valorTransacao, String descricao, LocalDate data){
        this.codigoTransacao=codigoTransacao;
        this.valorTransacao=valorTransacao;
        this.descricao=descricao;
        this.data=data;
        this.descricao=descricao;
    }

    public Transacao(double valorTransacao, String descricao, LocalDate data){
        this.valorTransacao=valorTransacao;
        this.descricao=descricao;
        this.data=data;
    }

    public Transacao() {

    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    public abstract String getresumo();

    public int getCodigoTransacao() {
        return codigoTransacao;
    }

    public void setCodigoTransacao(int codigoTransacao) {
        this.codigoTransacao = codigoTransacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}