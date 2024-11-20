<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Fintech - Adicionar Meta</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
  <link rel="stylesheet" href="./resources/css/footer.css">
</head>
<body>

<%@include file="header.jsp"%>

<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-md-6">
      <h3 class="text-center">Adicionar Nova Meta</h3>

      <!-- Exibir mensagem de erro, se houver -->

      <c:if test="${not empty mensagem}">
        <div class="alert alert-success ms-2 me-2 m-auto">${mensagem}</div>
      </c:if>

      <c:if test="${not empty erro}">
        <div class="alert alert-danger">${erro}</div>
      </c:if>

      <div class="card">
        <div class="card-body">
          <form action="meta?acao=cadastrar" method="post">

            <div class="form-group mb-3">
              <label for="id-nomeMeta">Nome da Meta</label>
              <input type="text" name="nomeMeta" id="id-nomeMeta" class="form-control" required>
            </div>

            <div class="form-group mb-3">
              <label for="id-valorMeta">Valor da Meta</label>
              <input type="number" step="0.01" name="valorMeta" id="id-valorMeta" class="form-control" required>
            </div>

            <div class="form-group mb-3">
              <label for="id-dataMeta">Data da Meta</label>
              <input type="date" name="dataMeta" id="id-dataMeta" class="form-control" required>
            </div>

            <div class="form-group mb-3">
              <label for="id-descricaoMeta">Descrição</label>
              <textarea name="descricaoMeta" id="id-descricaoMeta" class="form-control" rows="3" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary mt-3">Salvar Meta</button>
            <a href="meta?acao=listar" class="btn btn-secondary mt-3">Cancelar</a>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
