package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Recebimento extends Transacao {

    private int codRes;  // Identificador exclusivo para Recebimento

    // Construtor completo com todos os parâmetros
    public Recebimento(int codRes, int codigoTransacao, double valorTransacao, String descricao, LocalDate data) {
        super(codigoTransacao, codigoTransacao, valorTransacao, descricao, data);
        this.codRes = codRes;
    }

    // Construtor para listagem, onde apenas codRes e os detalhes principais são necessários
    public Recebimento(int codRes, double valorTransacao, String descricao, LocalDate data) {
        super(valorTransacao, descricao, data);
        this.codRes = codRes;
    }

    // Construtor simplificado para novos registros
    public Recebimento(double valorTransacao, String descricao, LocalDate data) {
        super(valorTransacao, descricao, data);
    }

    // Getter e Setter para codRes
    public int getCodRes() {
        return codRes;
    }

    public void setCodRes(int codRes) {
        this.codRes = codRes;
    }

    // Implementação do metodo abstrato da classe Transacao
    @Override
    public String getresumo() {
        return "Recebimento cadastrado!!\n" + getValorTransacao() + "\n" + getDescricao() + "\n" + getData() + "\n";
    }
}
