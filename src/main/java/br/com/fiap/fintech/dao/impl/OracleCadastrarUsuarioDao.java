package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.CadastrarUsuarioDao;
import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleCadastrarUsuarioDao implements CadastrarUsuarioDao {
    private Connection conexao ;
    @Override
    public boolean cadastrarUsuario(Usuario usuario) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO usuario (cd_usuario,nm_usuario, email_ususario, senha_usuario, genero, dt_nascimento) "
                    + "VALUES (sequencia_usuario.nextval,?, ?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getGenero());
            stmt.setDate(5, Date.valueOf(usuario.getDataNascimento()));

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
        return false;
    }
}
