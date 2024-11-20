package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.*;
import br.com.fiap.fintech.dao.impl.*;

public class DaoFactory {

    public static GastosDao getGastosDao() {
        return new OracleGastosDao();
    }
    public static RecebimentoDao getRecebimentoDao() {
        return new OracleRecebimentoDao();
    }
    public static MetaDao getMetaDao() {
        return new OracleMetaDao();
    }
    public static InvestimentoDao getInvestimentoDao() {
        return new OracleInvestimentoDao();
    }
    public static UsuarioDao getUsuarioDao() {
        return new OracleUsuarioDao();
    }
    public static CadastrarUsuarioDao getCadastrarUsuarioDao() {
        return new OracleCadastrarUsuarioDao();
    }
}
