package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.GastosDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Gastos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleGastosDao implements GastosDao {

    private Connection conexao;

    @Override
    public void cadastraGastos(Gastos gastos) throws DBEexception {
        PreparedStatement stmt = null ;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        String sql = "INSERT INTO gastos (cd_gastos, usuario_cd_usuario, " +
                "vl_gasto, dt_gasto, ds_gasto)" +
                " VALUES (sequencia_gastos.nextval, 1, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, gastos.getValorTransacao());
            stmt.setDate(2, Date.valueOf(gastos.getData()));
            stmt.setString(3, gastos.getDescricao());
            stmt.executeUpdate();

            System.out.println("Gastos cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new DBEexception("Erro ao cadastrar gastos");
        }finally {
            try{stmt.close();
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void atualizarGastos(Gastos gastos) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE gastos SET vl_gasto = ?, ds_gasto = ?, dt_gasto = ? WHERE cd_gastos = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, gastos.getValorTransacao());
            stmt.setString(2, gastos.getDescricao());
            stmt.setDate(3, Date.valueOf(gastos.getData()));
            stmt.setInt(4, gastos.getCodGastos());  // Usando codGastos como identificador para atualização
            stmt.executeUpdate();
            System.out.println("Gasto atualizado com sucesso!");
        } catch (SQLException e) {
            throw new DBEexception("Erro ao atualizar Gasto");
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
    public void removerGastos(int codigo) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM gastos WHERE cd_gastos=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Gastos removido com sucesso!");
        } catch (SQLException e) {
            throw new DBEexception("Erro ao remover gastos");
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
    public Gastos consultaGastos(int codigo) {
        Gastos gastos = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM gastos WHERE cd_gastos=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,codigo);
            rs=stmt.executeQuery();

            if (rs.next()){
                int codGastos = rs.getInt("cd_gastos");
                int id = rs.getInt("usuario_cd_usuario");
                double valor = rs.getDouble("vl_gasto");
                String descr = rs.getString("ds_gasto");
                LocalDate dia = rs.getDate("dt_gasto").toLocalDate();
                gastos = new Gastos(codGastos, id, valor, descr, dia);

            }
            System.out.println("Gastos consultado com sucesso!");
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
        return gastos;
    }

    @Override
    public List<Gastos> listarGastos() {
        List<Gastos> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try{

            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM gastos";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                int codGastos = rs.getInt("cd_gastos");
                double valor = rs.getDouble("vl_gasto");
                String descr = rs.getString("ds_gasto");
                LocalDate dia = rs.getDate("dt_gasto").toLocalDate();
                Gastos gasto = new Gastos(codGastos, valor, descr, dia);
                lista.add(gasto);
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
