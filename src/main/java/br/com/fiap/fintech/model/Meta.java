package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Meta {
    private String nomeMeta;
    private double valorMeta;
    private LocalDate dataMeta;
    private String descricaoMeta;
    private int codigoMeta;
    private int idMeta;

    public Meta(int codigoMeta,int idMeta,String nomeMeta,LocalDate dataMeta, double valorMeta, String descricaoMeta) {
        this.codigoMeta = codigoMeta;
        this.idMeta = idMeta;
        this.nomeMeta = nomeMeta;
        this.dataMeta = dataMeta;
        this.valorMeta = valorMeta;
        this.descricaoMeta = descricaoMeta;

    }

    public Meta(String nomeMeta, double valorMeta){
        this.nomeMeta=nomeMeta;
        this.valorMeta=valorMeta;
    }
    public Meta(String nomeMeta,LocalDate dataMeta, double valorMeta, String descricaoMeta){
        this.nomeMeta=nomeMeta;
        this.dataMeta=dataMeta;
        this.valorMeta=valorMeta;
        this.descricaoMeta=descricaoMeta;
    }

    public Meta(){
    }

    public Meta(String nomeMeta, double valorMeta, LocalDate dataMeta , String descricaoMeta){
        this.nomeMeta=nomeMeta;
        this.valorMeta=valorMeta;
        this.dataMeta=dataMeta;
        this.descricaoMeta=descricaoMeta;

    }

    public Meta(int codigoMeta, String nomeMeta, LocalDate dataMeta, double valorMeta, String descricaoMeta) {
        this.codigoMeta = codigoMeta;
        this.nomeMeta = nomeMeta;
        this.dataMeta = dataMeta;
        this.valorMeta = valorMeta;
        this.descricaoMeta = descricaoMeta;
    }



    public String getDescricaoMeta() {
        return descricaoMeta;
    }

    public void setDescricaoMeta(String descricaoMeta) {
        this.descricaoMeta = descricaoMeta;
    }

    public LocalDate getDataMeta() {
        return dataMeta;
    }

    public void setDataMeta(LocalDate dataMeta) {
        this.dataMeta = dataMeta;
    }

    public String getNomeMeta() {
        return nomeMeta;
    }

    public void setNomeMeta(String nomeMeta) {
        this.nomeMeta = nomeMeta;
    }

    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public int getCodigoMeta() {
        return codigoMeta;
    }

    public void setCodigoMeta(int codigoMeta) {
        this.codigoMeta = codigoMeta;
    }

    public int getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }
}
