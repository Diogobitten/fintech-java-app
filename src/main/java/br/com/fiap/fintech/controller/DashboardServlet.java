package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.InvestimentoDao;
import br.com.fiap.fintech.dao.MetaDao;
import br.com.fiap.fintech.dao.GastosDao;
import br.com.fiap.fintech.dao.RecebimentoDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.model.Meta;
import br.com.fiap.fintech.model.Gastos;
import br.com.fiap.fintech.model.Recebimento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private InvestimentoDao investimentoDao;
    private MetaDao metaDao;
    private GastosDao gastosDao;
    private RecebimentoDao recebimentoDao;

    @Override
    public void init() throws ServletException {
        investimentoDao = DaoFactory.getInvestimentoDao();
        metaDao = DaoFactory.getMetaDao();
        gastosDao = DaoFactory.getGastosDao();
        recebimentoDao = DaoFactory.getRecebimentoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if ("exibir".equals(acao)) {
            exibirDashboard(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida");
        }
    }

    private void exibirDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obter os dados diretamente dos DAOs e passá-los como atributos da requisição
            List<Investimento> investimentos = investimentoDao.listarInvestimentos();
            List<Meta> metas = metaDao.listarMeta();
            List<Gastos> gastos = gastosDao.listarGastos();
            List<Recebimento> recebimentos = recebimentoDao.listarRecebimento();

            // Atribuindo listas aos atributos da requisição
            req.setAttribute("investimentos", investimentos);
            req.setAttribute("metas", metas);
            req.setAttribute("gastos", gastos);
            req.setAttribute("recebimentos", recebimentos);

            // Encaminhando para o JSP do dashboard
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);

        } catch (DBEexception e) {
            throw new ServletException("Erro ao buscar dados para o dashboard", e);
        }
    }
}
