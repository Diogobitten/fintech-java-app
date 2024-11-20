package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Gastos extends Transacao {

    private int codGastos;  // Identificador exclusivo para Gastos

    // Construtor completo
    public Gastos(int codGastos, int codigoTransacao, double valorTransacao, String descricao, LocalDate data) {
        super(codigoTransacao, codigoTransacao, valorTransacao, descricao, data);
        this.codGastos = codGastos;
    }

    // Construtor para casos de listagem onde apenas o codGastos e detalhes são necessários
    public Gastos(int codGastos, double valorTransacao, String descricao, LocalDate data) {
        super(valorTransacao, descricao, data);
        this.codGastos = codGastos;
    }

    // Construtor simplificado para novos registros
    public Gastos(double valorTransacao, String descricao, LocalDate data) {
        super(valorTransacao, descricao, data);
    }

    // Getter e Setter para codGastos
    public int getCodGastos() {
        return codGastos;
    }

    public void setCodGastos(int codGastos) {
        this.codGastos = codGastos;
    }

    // Implementação do método abstrato da classe Transacao
    @Override
    public String getresumo() {
        return "Gasto cadastrado!!\n" + getValorTransacao() + "\n" + getDescricao() + "\n" + getData() + "\n";
    }
}
