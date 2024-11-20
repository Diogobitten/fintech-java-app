package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.RecebimentoDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Recebimento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleRecebimentoDao implements RecebimentoDao {
    private Connection conexao;

    @Override
    public void cadastrarRecebimento(Recebimento recebimento) throws DBEexception {
        PreparedStatement stmt = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO recebimentos (cd_recebimento, usuario_cd_usuario, vl_recebimento, " +
                "dt_recebimento, ds_recebimento) VALUES (sequencia_recebimentos.nextval, 1, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, recebimento.getValorTransacao());
            stmt.setDate(2, Date.valueOf(recebimento.getData()));
            stmt.setString(3, recebimento.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBEexception("Erro ao cadastrar recebimento", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizarRecebimento(Recebimento recebimento) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE recebimentos SET vl_recebimento = ?, dt_recebimento = ?, ds_recebimento = ? WHERE cd_recebimento = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, recebimento.getValorTransacao());
            stmt.setDate(2, Date.valueOf(recebimento.getData()));
            stmt.setString(3, recebimento.getDescricao());
            stmt.setInt(4, recebimento.getCodRes()); // Usando codRes como identificador
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBEexception("Erro ao atualizar recebimento", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removerRecebimento(int codigo) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM recebimentos WHERE cd_recebimento = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Recebimento removido com sucesso!");
        } catch (SQLException e) {
            throw new DBEexception("Erro ao remover recebimento");
        } finally {
            try {
                if (stmt != null) stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Recebimento consultarRecebimento(int codigo) throws DBEexception {
        Recebimento recebimento = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM recebimentos WHERE cd_recebimento = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int codRes = rs.getInt("cd_recebimento");
                double valor = rs.getDouble("vl_recebimento");
                String descr = rs.getString("ds_recebimento");
                LocalDate dia = rs.getDate("dt_recebimento").toLocalDate();
                recebimento = new Recebimento(codRes, valor, descr, dia);
            }
        } catch (SQLException e) {
            throw new DBEexception("Erro ao consultar recebimento", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recebimento;
    }

    @Override
    public List<Recebimento> listarRecebimento() throws DBEexception {
        List<Recebimento> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM recebimentos";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codRes = rs.getInt("cd_recebimento");
                double valor = rs.getDouble("vl_recebimento");
                String descr = rs.getString("ds_recebimento");
                LocalDate dia = rs.getDate("dt_recebimento").toLocalDate();
                Recebimento recebimento = new Recebimento(codRes, valor, descr, dia);
                lista.add(recebimento);
            }
        } catch (SQLException e) {
            throw new DBEexception("Erro ao listar recebimentos", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
