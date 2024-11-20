package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Meta;

import java.util.List;

public interface MetaDao {
    void cadastrarMeta(Meta meta) throws DBEexception;
    void atualizarMeta(Meta meta) throws DBEexception;
    void removerMeta(int codigo) throws DBEexception;
    Meta consultarMeta(int codigo) throws DBEexception;
    List<Meta> listarMeta() throws DBEexception;

}

