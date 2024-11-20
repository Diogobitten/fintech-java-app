<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Fintech - Cadastro</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header text-center">
          <h3>Cadastro</h3>
        </div>

        <div class="card-body">
          <!-- Exibir mensagem de sucesso ou erro -->
          <c:if test="${not empty mensagem}">
            <div class="alert alert-success ms-2 me-2 m-auto">${mensagem}</div>
          </c:if>

          <c:if test="${not empty erro}">
            <div class="alert-danger ms-2 me-2 m-auto">${erro}</div>
          </c:if>

          <!-- Formulário de Cadastro -->
          <form action="cadastro" method="post">
            <div class="mb-3">
              <label for="nome" class="form-label">Nome</label>
              <input type="text" class="form-control" id="nome" name="nome" required>
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
              <label for="senha" class="form-label">Senha</label>
              <input type="password" class="form-control" id="senha" name="senha" required>
            </div>
            <div class="mb-3">
              <label for="genero" class="form-label">Gênero</label>
              <select class="form-control" id="genero" name="genero" required>
                <option value="">Selecione</option>
                <option value="masculino">Masculino</option>
                <option value="feminino">Feminino</option>
                <option value="outro">Outro</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="dataNascimento" class="form-label">Data de Nascimento</label>
              <input type="date" class="form-control" id="dataNascimento" name="dataNascimento" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
          </form>
        </div>

        <div class="card-footer text-center">
          <p>Já tem uma conta? <a href="index.jsp">Entre</a></p>
        </div>
      </div>
    </div>
  </div>
</div>


<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
