package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.RecebimentoDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Recebimento;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/recebimento")
public class RecebimentoServlet extends HttpServlet {

    private RecebimentoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getRecebimentoDao();
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
                break;
        }
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.removerRecebimento(codigo);
            req.setAttribute("mensagem", "Recebimento removido com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao excluir recebimento");
        }
        listar(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double valorTransacao = Double.parseDouble(req.getParameter("valorTransacao"));
        String descricao = req.getParameter("descricao");
        LocalDate data = LocalDate.parse(req.getParameter("data"));

        Recebimento recebimento = new Recebimento(0, valorTransacao, descricao, data);

        try {
            dao.cadastrarRecebimento(recebimento);
            req.setAttribute("mensagem", "Recebimento cadastrado com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar recebimento");
        }

        req.getRequestDispatcher("recebimento.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codRes = Integer.parseInt(req.getParameter("codRes"));
            double valorTransacao = Double.parseDouble(req.getParameter("valorTransacao"));
            String descricao = req.getParameter("descricao");
            LocalDate data = LocalDate.parse(req.getParameter("data"));

            Recebimento recebimento = new Recebimento(codRes, valorTransacao, descricao, data);
            dao.atualizarRecebimento(recebimento);

            req.setAttribute("mensagem", "Recebimento editado com sucesso!");
        } catch (DBEexception db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao editar recebimento");
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
        int codRes = Integer.parseInt(req.getParameter("codigo"));
        Recebimento recebimento = null;
        try {
            recebimento = dao.consultarRecebimento(codRes);
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("recebimento", recebimento);
        req.getRequestDispatcher("editar-recebimento.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recebimento> lista = null;
        try {
            lista = dao.listarRecebimento();
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("recebimento", lista);
        req.getRequestDispatcher("lista-recebimento.jsp").forward(req, resp);
    }
}
