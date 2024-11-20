package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Investimento {
    private String tipoInvestimento;
    private String nomeInvestimento;
    private double valorInvestimento;
    private LocalDate dataInvestimento;
    private LocalDate dataVencimento;
    private int codigoInvestimento;
    private String nomeBanco;

    public Investimento(String tipoInvestimento, String nomeInvestimento,String nomeBanco, double valorInvestimento, LocalDate dataInvestimento, LocalDate dataVencimento){
        this.nomeBanco = nomeBanco;
        this.nomeInvestimento=nomeInvestimento;
        this.valorInvestimento=valorInvestimento;
        this.dataInvestimento=dataInvestimento;
        this.dataVencimento=dataVencimento;
        this.tipoInvestimento=tipoInvestimento;
    }

    public Investimento(int codigoInvestimento, String tipoInvestimento, String nomeInvestimento, String nomeBanco, double valorInvestimento, LocalDate dataInvestimento, LocalDate dataVencimento) {
        this.codigoInvestimento = codigoInvestimento;
        this.nomeBanco = nomeBanco;
        this.nomeInvestimento = nomeInvestimento;
        this.valorInvestimento = valorInvestimento;
        this.dataInvestimento = dataInvestimento;
        this.dataVencimento = dataVencimento;
        this.tipoInvestimento = tipoInvestimento;
    }

    public Investimento(String banco, Double valor, LocalDate dataInvestimento, LocalDate dataVencimento, String tipo) {
    }


    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }

    public LocalDate getDataInvestimento() {
        return dataInvestimento;
    }

    public void setDataInvestimento(LocalDate dataInvestimento) {
        this.dataInvestimento = dataInvestimento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public int getCodigoInvestimento() {
        return codigoInvestimento;
    }

    public void setCodigoInvestimento(int codigoInvestimento) {
        this.codigoInvestimento = codigoInvestimento;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
}

