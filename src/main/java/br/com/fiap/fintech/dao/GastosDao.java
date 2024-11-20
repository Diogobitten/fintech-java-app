package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Gastos;

import java.util.List;

public interface GastosDao {
    void cadastraGastos(Gastos gastos)throws DBEexception;
    void atualizarGastos(Gastos gastos)throws DBEexception;
    void removerGastos(int id)throws DBEexception;
    Gastos consultaGastos(int codigo);
    List<Gastos> listarGastos();

}
