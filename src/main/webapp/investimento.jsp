<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro do Investimento</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/footer.css">

</head>
<body>

<%@include file="header.jsp"%>


<div class="container">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
                CADASTRO DE INVESTIMENTO
            </div>

            <c:if test="${not empty mensagem}">
                <div class="alert alert-success ms-2 me-2 m-auto">${mensagem}</div>
            </c:if>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger ms-2 me-2 m-auto">${erro}</div>
            </c:if>


            <div class="card-body">
                <form action="investimento?acao=cadastrar" method="post">
                    <div class="form-group">
                        <label for="id-nomeBanco">Banco</label>
                        <input type="text" name="nomeBanco" id="id-nomeBanco" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="id-nomeInvestimento">Nome do Investimento</label>
                        <input type="text" name="nomeInvestimento" id="id-nomeInvestimento" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="id-valorInvestimento">Valor</label>
                        <input type="number" step="0.01" name="valorInvestimento" id="id-valorInvestimento" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="id-dataInvestimento">Data de Investimento</label>
                        <input type="date" name="dataInvestimento" id="id-dataInvestimento" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="id-dataVencimento">Data de Vencimento</label>
                        <input type="date" name="dataVencimento" id="id-dataVencimento" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="id-tipoInvestimento">Tipo</label>
                        <input type="text" name="tipoInvestimento" id="id-tipoInvestimento" class="form-control" required>
                    </div>
                    <input type="submit" value="Salvar" class="btn btn-primary mt-3">
                    <a href="investimento?acao=listar" class="btn btn-secondary mt-3">Voltar para Investimento</a>

                </form>
            </div>
        </div>
    </div>
</div>




<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
