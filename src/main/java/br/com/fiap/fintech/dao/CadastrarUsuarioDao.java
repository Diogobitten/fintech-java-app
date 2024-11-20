package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Usuario;

public interface CadastrarUsuarioDao  {
    boolean cadastrarUsuario(Usuario usuario)throws DBEexception;
}
