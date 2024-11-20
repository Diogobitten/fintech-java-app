package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.InvestimentoDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Gastos;
import br.com.fiap.fintech.model.Investimento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleInvestimentoDao implements InvestimentoDao {
    private Connection conexao;
    @Override
    public void cadastrarInvestimento(Investimento investimento) throws DBEexception {
        PreparedStatement stmt = null;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        String sql = "INSERT INTO investimento (cd_investimento, tipo_investimento," +
                " nm_aplicacao, nm_banco, vl_investimento, dt_realizacao_invest," + " dt_vencimento)" +
                "VALUES (sequencia_investimentos.nextval,?,?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, investimento.getTipoInvestimento());
            stmt.setString(2, investimento.getNomeInvestimento());
            stmt.setString(3, investimento.getNomeBanco());
            stmt.setDouble(4, investimento.getValorInvestimento());
            stmt.setDate(5, Date.valueOf(investimento.getDataInvestimento()));
            stmt.setDate(6, Date.valueOf(investimento.getDataVencimento()));
            stmt.executeUpdate();
            System.out.println("Investimento cadastrado com sucesso!");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{stmt.close();
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizarInvestimento(Investimento investimento) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE investimento SET tipo_investimento=?,nm_aplicacao=?,nm_banco=?,vl_investimento=?,dt_realizacao_invest=?,dt_vencimento=? " +
                    "WHERE cd_investimento=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, investimento.getTipoInvestimento());
            stmt.setString(2, investimento.getNomeInvestimento());
            stmt.setString(3, investimento.getNomeBanco());
            stmt.setDouble(4, investimento.getValorInvestimento());
            stmt.setDate(5, Date.valueOf(investimento.getDataInvestimento()));
            stmt.setDate(6, Date.valueOf(investimento.getDataVencimento()));
            stmt.setInt(7, investimento.getCodigoInvestimento());
            stmt.executeUpdate();
            System.out.println("Investimento atualizado com sucesso!");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void removerInvestimento(int codigo) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM investimento WHERE cd_investimento=?";
            stmt= conexao.prepareStatement(sql);
            stmt.setInt(1,codigo);
            stmt.executeUpdate();
            System.out.println("Investimento removido com sucesso!");

        }catch (SQLException e) {
            throw new DBEexception("Erro ao remover investimento");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public Investimento consultarInvestimento(int codigo) throws DBEexception {
        Investimento investimento = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM investimento WHERE cd_investimento=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int codigoInvestimento = rs.getInt("cd_investimento");
                String tipoInvest = rs.getString("tipo_investimento");
                String nomeAplicacao = rs.getString("nm_aplicacao");
                String nomeBanco = rs.getString("nm_banco");
                double valorInvest = rs.getDouble("vl_investimento");
                LocalDate dataInvest = rs.getDate("dt_realizacao_invest").toLocalDate();
                LocalDate dataVenc = rs.getDate("dt_vencimento").toLocalDate();

                // Instancia o objeto Investimento com os valores do banco de dados
                investimento = new Investimento(tipoInvest, nomeAplicacao, nomeBanco, valorInvest, dataInvest, dataVenc);
                investimento.setCodigoInvestimento(codigoInvestimento);
            } else {
                System.out.println("Nenhum investimento encontrado com o código: " + codigo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return investimento;
    }

    @Override
    public List<Investimento> listarInvestimentos() throws DBEexception {
        List<Investimento> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try{

            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM investimento order by dt_realizacao_invest desc";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                int codigoInvestimento = rs.getInt("cd_investimento");
                String tipoInvest = rs.getString("tipo_investimento");
                String nomeAplicacao = rs.getString("nm_aplicacao");
                String nomeBanco = rs.getString("nm_banco");
                double valorInvest = rs.getDouble("vl_investimento");
                LocalDate dataInvest = rs.getDate("dt_realizacao_invest").toLocalDate();
                LocalDate dataVenc = rs.getDate("dt_vencimento").toLocalDate();
                Investimento investimento = new Investimento(tipoInvest,nomeAplicacao,nomeBanco,valorInvest,dataInvest,dataVenc);
                investimento.setCodigoInvestimento(codigoInvestimento);
                lista.add(investimento);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }



}
