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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/download-excel")
public class DownloadExcelServlet extends HttpServlet {

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
        String tipo = req.getParameter("tipo");

        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "attachment; filename=" + tipo + ".xlsx");

        try (Workbook workbook = new XSSFWorkbook(); OutputStream out = resp.getOutputStream()) {
            if ("investimento".equalsIgnoreCase(tipo)) {
                gerarExcelInvestimentos(workbook);
            } else if ("meta".equalsIgnoreCase(tipo)) {
                gerarExcelMetas(workbook);
            } else if ("gasto".equalsIgnoreCase(tipo)) {
                gerarExcelGastos(workbook);
            } else if ("recebimento".equalsIgnoreCase(tipo)) {
                gerarExcelRecebimentos(workbook);
            } else {
                throw new ServletException("Tipo inválido para download de Excel");
            }

            workbook.write(out);
        } catch (Exception e) {
            throw new ServletException("Erro ao gerar o arquivo Excel", e);
        }
    }

    private void gerarExcelInvestimentos(Workbook workbook) throws DBEexception {
        Sheet sheet = workbook.createSheet("Investimentos");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Banco");
        header.createCell(1).setCellValue("Nome do Investimento");
        header.createCell(2).setCellValue("Valor do Investimento");
        header.createCell(3).setCellValue("Data do Investimento");
        header.createCell(4).setCellValue("Data de Vencimento");
        header.createCell(5).setCellValue("Tipo de Investimento");

        List<Investimento> investimentos = investimentoDao.listarInvestimentos();
        int rowIndex = 1;
        for (Investimento investimento : investimentos) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(investimento.getNomeBanco());
            row.createCell(1).setCellValue(investimento.getNomeInvestimento());
            row.createCell(2).setCellValue(investimento.getValorInvestimento());
            row.createCell(3).setCellValue(investimento.getDataInvestimento().toString());
            row.createCell(4).setCellValue(investimento.getDataVencimento().toString());
            row.createCell(5).setCellValue(investimento.getTipoInvestimento());
        }
    }

    private void gerarExcelMetas(Workbook workbook) throws DBEexception {
        Sheet sheet = workbook.createSheet("Metas");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nome da Meta");
        header.createCell(1).setCellValue("Descrição");
        header.createCell(2).setCellValue("Valor Objetivo");
        header.createCell(3).setCellValue("Data de Criação");
        header.createCell(4).setCellValue("Data de Conclusão");

        List<Meta> metas = metaDao.listarMeta();
        int rowIndex = 1;
        for (Meta meta : metas) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(meta.getNomeMeta());
            row.createCell(1).setCellValue(meta.getDescricaoMeta());
            row.createCell(2).setCellValue(meta.getValorMeta());
            row.createCell(3).setCellValue(meta.getDataMeta().toString());
            row.createCell(4).setCellValue(meta.getDataMeta().toString());
        }
    }

    private void gerarExcelGastos(Workbook workbook) throws DBEexception {
        Sheet sheet = workbook.createSheet("Gastos");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Descrição");
        header.createCell(1).setCellValue("Valor do Gasto");
        header.createCell(2).setCellValue("Data do Gasto");

        List<Gastos> gastos = gastosDao.listarGastos();
        int rowIndex = 1;
        for (Gastos gasto : gastos) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(gasto.getDescricao());
            row.createCell(1).setCellValue(gasto.getValorTransacao());
            row.createCell(2).setCellValue(gasto.getData().toString());
        }
    }

    private void gerarExcelRecebimentos(Workbook workbook) throws DBEexception {
        Sheet sheet = workbook.createSheet("Recebimentos");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Descrição");
        header.createCell(1).setCellValue("Valor do Recebimento");
        header.createCell(2).setCellValue("Data do Recebimento");

        List<Recebimento> recebimentos = recebimentoDao.listarRecebimento();
        int rowIndex = 1;
        for (Recebimento recebimento : recebimentos) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(recebimento.getDescricao());
            row.createCell(1).setCellValue(recebimento.getValorTransacao());
            row.createCell(2).setCellValue(recebimento.getData().toString());
        }
    }
}
