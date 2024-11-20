package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.MetaDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Meta;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/meta")
public class MetaServlet extends HttpServlet {

    private MetaDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getMetaDao();
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
            dao.removerMeta(codigo);
            req.setAttribute("mensagem", "Meta removida com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao excluir meta");
        }
        listar(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeMeta = req.getParameter("nomeMeta");
        double valorMeta = Double.parseDouble(req.getParameter("valorMeta"));
        LocalDate dataMeta = LocalDate.parse(req.getParameter("dataMeta"));
        String descricaoMeta = req.getParameter("descricaoMeta");

        Meta meta = new Meta(
                0,
                nomeMeta,
                dataMeta,
                valorMeta,
                descricaoMeta

        );

        try {
            dao.cadastrarMeta(meta);
            req.setAttribute("mensagem", "Meta cadastrada com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar meta");
        }

        req.getRequestDispatcher("meta.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoMeta = Integer.parseInt(req.getParameter("codigoMeta"));
            String nomeMeta = req.getParameter("nomeMeta");
            double valorMeta = Double.parseDouble(req.getParameter("valorMeta"));
            LocalDate dataMeta = LocalDate.parse(req.getParameter("dataMeta"));
            String descricaoMeta = req.getParameter("descricaoMeta");

            Meta meta = new Meta(codigoMeta, nomeMeta, dataMeta, valorMeta, descricaoMeta);
            dao.atualizarMeta(meta);

            req.setAttribute("mensagem", "Meta editada com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao editar meta");
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
        Meta meta = null;
        try {
            meta = dao.consultarMeta(id);
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("meta", meta);
        req.getRequestDispatcher("editar-meta.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Meta> lista = null;
        try {
            lista = dao.listarMeta();
        } catch (DBEexception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("meta", lista);
        req.getRequestDispatcher("lista-meta.jsp").forward(req, resp);
    }
}
