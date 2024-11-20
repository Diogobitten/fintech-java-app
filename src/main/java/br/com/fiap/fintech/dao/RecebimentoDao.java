package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Recebimento;

import java.util.List;

public interface RecebimentoDao {
    void cadastrarRecebimento(Recebimento recebimento)throws DBEexception;
    void atualizarRecebimento(Recebimento recebimento)throws DBEexception;
    void removerRecebimento(int codigo)throws DBEexception;
    Recebimento consultarRecebimento(int codigo) throws DBEexception;
    List<Recebimento> listarRecebimento() throws DBEexception;
}
