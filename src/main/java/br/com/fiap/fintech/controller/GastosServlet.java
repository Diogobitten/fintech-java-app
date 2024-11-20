package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.GastosDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Gastos;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/gastos")
public class GastosServlet extends HttpServlet {

    private GastosDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getGastosDao();
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
        int codGastos = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.removerGastos(codGastos);
            req.setAttribute("mensagem", "Gasto removido com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao excluir gasto");
        }
        listar(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String descricao = req.getParameter("descricao");
        double valor = Double.parseDouble(req.getParameter("valor"));
        LocalDate data = LocalDate.parse(req.getParameter("data"));

        Gastos gasto = new Gastos(0, valor, descricao, data);

        try {
            dao.cadastraGastos(gasto);
            req.setAttribute("mensagem", "Gasto cadastrado com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar gasto");
        }

        req.getRequestDispatcher("gastos.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codGastos = Integer.parseInt(req.getParameter("codGastos"));
            String descricao = req.getParameter("descricao");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate.parse(req.getParameter("data"));

            Gastos gasto = new Gastos(codGastos, valor, descricao, data);

            dao.atualizarGastos(gasto);
            req.setAttribute("mensagem", "Gasto editado com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao editar gasto");
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
        int codGastos = Integer.parseInt(req.getParameter("codigo"));
        Gastos gasto = dao.consultaGastos(codGastos);
        req.setAttribute("gasto", gasto);
        req.getRequestDispatcher("editar-gastos.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Gastos> lista = dao.listarGastos();
        req.setAttribute("gastos", lista);
        req.getRequestDispatcher("lista-gastos.jsp").forward(req, resp);
    }
}
