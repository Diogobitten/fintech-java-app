package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.InvestimentoDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Investimento;

import java.time.LocalDate;

public class ProdutoDaoTeste2 {

    public static void main(String[] args) {

        //cadastrar Investimento
        InvestimentoDao dao = DaoFactory.getInvestimentoDao();

        Investimento investimento = new Investimento(
                0,
                "oi",
                "oi",
                "Nubank",
                22,
                LocalDate.of(2024,10,21),
                LocalDate.of(2024,11,21)

        );

        try {
            dao.cadastrarInvestimento(investimento);
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }

    }
}
