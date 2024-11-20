package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.util.CriptografiaUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleUsuarioDao implements UsuarioDao {
    private Connection conexao;

    @Override
    public boolean validarUsuario(Usuario usuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            // Consulta apenas pelo email
            String sql = "SELECT senha_usuario FROM usuario WHERE email_ususario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtém a senha criptografada armazenada no banco de dados
                String senhaCriptografadaBanco = rs.getString("senha_usuario");

                // Criptografa a senha informada pelo usuário
                String senhaCriptografadaInformada = CriptografiaUtils.criptografar(usuario.getSenha());

                // Compara as senhas criptografadas
                return senhaCriptografadaBanco.equals(senhaCriptografadaInformada);
            }

        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
