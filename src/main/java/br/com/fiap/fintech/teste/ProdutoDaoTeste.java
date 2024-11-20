package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.CadastrarUsuarioDao;
import br.com.fiap.fintech.dao.InvestimentoDao;
import br.com.fiap.fintech.dao.MetaDao;
import br.com.fiap.fintech.dao.RecebimentoDao;
import br.com.fiap.fintech.dao.impl.OracleCadastrarUsuarioDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.model.Meta;
import br.com.fiap.fintech.model.Recebimento;
import br.com.fiap.fintech.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class ProdutoDaoTeste {
    public static void main(String[] args) {

        CadastrarUsuarioDao dao = DaoFactory.getCadastrarUsuarioDao();

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Joao");
        novoUsuario.setEmail("maria@exemplo.com");
        novoUsuario.setSenha("123"); // A senha será criptografada automaticamente
        novoUsuario.setGenero("Feminino");
        novoUsuario.setDataNascimento(LocalDate.of(1998, 3, 15));

// Verifique a senha criptografada
        System.out.println("Senha criptografada: " + novoUsuario.getSenha());

        OracleCadastrarUsuarioDao usuarioDao = new OracleCadastrarUsuarioDao();
        try {
            boolean sucesso = usuarioDao.cadastrarUsuario(novoUsuario);
            if (sucesso) {
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar usuário.");
            }
        } catch (DBEexception e) {
            e.printStackTrace();
        }

//        try {
//            investimento = dao.consultarInvestimento(1);
//        } catch (DBEexception e) {
//            throw new RuntimeException(e);
//        }
//       investimento.setNomeBanco("Mamacoprego");
//       investimento.setValorInvestimento(66666);
//       try {
//           dao.atualizarInvestimento(investimento);
//           System.out.println();
//       }catch (DBEexception e){
//           e.printStackTrace();
//       }
//       try {
//           dao.removerInvestimento(1);
//           System.out.println("Gastos removido com sucesso!");
//       } catch (DBEexception e) {
//           throw new RuntimeException(e);
//       }
//        List<Investimento> lista = null;
//        try {
//            lista = dao.listarInvestimentos();
//        } catch (DBEexception e) {
//            throw new RuntimeException(e);
//        }
//        for (Investimento i : lista) {
//            System.out.println(i.getTipoInvestimento()+" | "+ i.getNomeInvestimento() +" | "+ i.getNomeBanco() +" | "+ i.getValorInvestimento()+" | "+ i.getDataInvestimento()+" | "+i.getDataVencimento());
//        }

    }
}
