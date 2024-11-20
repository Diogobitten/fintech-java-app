package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.InvestimentoDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Investimento;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {

    private InvestimentoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getInvestimentoDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {

            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);

        }


    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.removerInvestimento(codigo);
            req.setAttribute("mensagem", "Investimento removido com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");

        }
        listar(req, resp);

    }


    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeBanco = req.getParameter("nomeBanco");
        String nomeInvestimento = req.getParameter("nomeInvestimento");
        double valorInvestimento = Double.parseDouble(req.getParameter("valorInvestimento"));
        LocalDate dataInvestimento = LocalDate.parse(req.getParameter("dataInvestimento"));
        LocalDate dataVencimento = LocalDate.parse(req.getParameter("dataVencimento"));
        String tipoInvestimento = req.getParameter("tipoInvestimento");

        Investimento investimento = new Investimento(
                0,
                tipoInvestimento,
                nomeInvestimento,
                nomeBanco,
                valorInvestimento,
                dataInvestimento,
                dataVencimento
        );

        try {
            dao.cadastrarInvestimento(investimento);
            req.setAttribute("mensagem", "Produto cadastrado com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar investimento");
        }

        req.getRequestDispatcher("investimento.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoInvestimento = Integer.parseInt(req.getParameter("codigoInvestimento"));
            String nomeBanco = req.getParameter("nomeBanco");
            String nomeInvestimento = req.getParameter("nomeInvestimento");
            double valorInvestimento = Double.parseDouble(req.getParameter("valorInvestimento"));
            LocalDate dataInvestimento = LocalDate.parse(req.getParameter("dataInvestimento"));
            LocalDate dataVencimento = LocalDate.parse(req.getParameter("dataVencimento"));
            String tipoInvestimento = req.getParameter("tipoInvestimento");


            Investimento investimento = new Investimento(codigoInvestimento, tipoInvestimento, nomeInvestimento, nomeBanco, valorInvestimento, dataInvestimento, dataVencimento);
            dao.atualizarInvestimento(investimento);

            req.setAttribute("mensagem", "Produto editado com sucesso!");
        } catch (DBEexception db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao editar investimento");
        } catch (Exception e) {
            e.printStackTrace();
        }
        listar(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
                break;
        }


    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Investimento investimento = null;
        try {
            investimento = dao.consultarInvestimento(id);
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("investimento", investimento);
        req.getRequestDispatcher("editar-investimento.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Investimento> lista = null;
        try {
            lista = dao.listarInvestimentos();
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("investimento", lista);

        req.getRequestDispatcher("lista-inv.jsp").forward(req, resp);
    }

}



