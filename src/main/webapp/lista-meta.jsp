<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>Fintech - Metas</title>
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
        LISTA DE METAS
      </div>
      <div class="card-body">
        <h5 class="card-title">Gerenciamento de metas eficiente</h5>
        <p class="card-text">Mantenha seus objetivos organizados e acessíveis.</p>
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th>Nome da Meta</th>
            <th class="text-center">Valor da Meta</th>
            <th class="text-center">Data da Meta</th>
            <th class="text-center">Descrição</th>
            <th class="text-center"></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${meta}" var="metas">
            <tr>
              <td>${metas.nomeMeta}</td>
              <td class="text-center">${metas.valorMeta}</td>
              <td class="text-center">
                <fmt:parseDate value="${metas.dataMeta}" pattern="yyy-MM-dd" var="dataMetaFmt"/>
                <fmt:formatDate value="${dataMetaFmt}" pattern="dd/MM/yyyy"/>
              </td>
              <td class="text-center">${metas.descricaoMeta}</td>
              <td class="text-center">
                <c:url value="meta" var="link">
                  <c:param name="acao" value="abrir-form-edicao"/>
                  <c:param name="codigo" value="${metas.codigoMeta}"/>
                </c:url>
                <a href="${link}" class="btn btn-primary">Editar</a>

                <!-- Botão para abrir a modal -->
                <button
                        type="button"
                        class="btn btn-danger"
                        data-bs-toggle="modal"
                        data-bs-target="#excluirModal"
                        onclick="codigoExcluir.value = ${metas.codigoMeta}"
                >
                  Excluir
                </button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <a href="meta.jsp" class="btn btn-success">Adicionar Meta</a>
        <a href="dash3.jsp" class="btn btn-secondary">Voltar</a>
        <a href="download-excel?tipo=meta" class="btn btn-warning">Baixar Metas em Excel</a>

      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar Exclusão</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h4>Você confirma a exclusão desta meta?</h4>
        <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
      </div>
      <div class="modal-footer">
        <form action="meta" method="post">
          <input type="hidden" name="acao" value="excluir">
          <input type="hidden" name="codigoExcluir" id="codigoExcluir">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
          <button type="submit" class="btn btn-danger">Sim</button>
        </form>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
