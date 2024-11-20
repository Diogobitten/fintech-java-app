package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.MetaDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.model.Gastos;
import br.com.fiap.fintech.model.Meta;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleMetaDao implements MetaDao {
    private Connection conexao;
    @Override
    public void cadastrarMeta(Meta meta) throws DBEexception {
        PreparedStatement stmt = null ;
        Connection conexao = ConnectionManager.getInstance().getConnection();
        String sql = "INSERT INTO obj_financeiro (cd_obj_fin,usuario_cd_usuario,nm_objetivo,dt_atingir_obj,vl_objetivo,ds_objetivo) Values (sequencia_meta.nextval,1,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, meta.getNomeMeta());
            stmt.setDate(2,Date.valueOf(meta.getDataMeta()));
            stmt.setDouble(3, meta.getValorMeta());
            stmt.setString(4, meta.getDescricaoMeta());
            stmt.executeUpdate();

            System.out.println("Meta cadastrada com sucesso!");
        } catch (SQLException e) {
            throw new DBEexception("Erro ao cadastrar Meta");
        }finally {
            try{stmt.close();
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizarMeta(Meta meta) throws DBEexception {
        PreparedStatement stmt = null ;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE obj_financeiro SET nm_objetivo = ?,dt_atingir_obj=?,vl_objetivo=?,ds_objetivo=? WHERE cd_obj_fin=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, meta.getNomeMeta());
            stmt.setDate(2,Date.valueOf(meta.getDataMeta()));
            stmt.setDouble(3, meta.getValorMeta());
            stmt.setString(4, meta.getDescricaoMeta());
            stmt.setInt(5,meta.getCodigoMeta());
            stmt.executeUpdate();
            System.out.println("Meta atualizada com sucesso!");
        }catch (SQLException e) {
            throw new DBEexception("Erro ao atualizar Gasto");
        }finally {
            try{stmt.close();
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void removerMeta(int codigo) throws DBEexception {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM obj_financeiro WHERE cd_obj_fin=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Meta removida com sucesso!");
        } catch (SQLException e) {
            throw new DBEexception("Erro ao remover Meta");
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
    public Meta consultarMeta(int codigo) throws DBEexception {
        Meta meta = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM obj_financeiro WHERE cd_obj_fin=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,codigo);
            rs=stmt.executeQuery();

            if (rs.next()){
                int codigoMeta = rs.getInt("cd_obj_fin");
                int id = rs.getInt("usuario_cd_usuario");
                String nomeMeta = rs.getString("nm_objetivo");
                LocalDate dataMeta = rs.getDate("dt_atingir_obj").toLocalDate();
                Double valorMeta = rs.getDouble("vl_objetivo");
                String descricaoMeta = rs.getString("ds_objetivo");

                meta = new Meta(codigoMeta,id,nomeMeta,dataMeta,valorMeta,descricaoMeta);

            }
            System.out.println("Meta consultada com sucesso!");
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
        return meta;
    }



    @Override
    public List<Meta> listarMeta() throws DBEexception {
        List<Meta> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;


            try{

                conexao = ConnectionManager.getInstance().getConnection();
                String sql = "SELECT * FROM obj_financeiro";
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()){
                    int codigoMeta = rs.getInt("cd_obj_fin");
                    String nomeMeta = rs.getString("nm_objetivo");
                    LocalDate dataMeta = rs.getDate("dt_atingir_obj").toLocalDate();
                    Double valorMeta = rs.getDouble("vl_objetivo");
                    String descricaoMeta = rs.getString("ds_objetivo");
                    Meta meta = new Meta( codigoMeta,nomeMeta,dataMeta,valorMeta,descricaoMeta);
                    lista.add(meta);
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

