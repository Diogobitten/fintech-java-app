package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Investimento;

import java.util.List;

public interface InvestimentoDao {
    void cadastrarInvestimento(Investimento investimento)throws DBEexception;
    void atualizarInvestimento(Investimento investimento)throws DBEexception;
    void removerInvestimento(int codigo)throws DBEexception;
    Investimento consultarInvestimento(int codigo)throws DBEexception;
    List<Investimento> listarInvestimentos()throws DBEexception;

}

