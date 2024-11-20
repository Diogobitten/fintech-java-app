<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
  <link rel="stylesheet" href="./resources/css/footer.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-5">
  <div class="text-center">
    <h2>Bem vindo!</h2>
    <p>Escolha uma das opções abaixo para gerenciar suas finanças:</p>
  </div>

  <!-- Card da Dashboard ocupando duas colunas -->
  <div class="row mt-4 text-center">
    <div class="col-md-12 mb-4">
      <div class="card text-center shadow-sm">
        <div class="card-body">
          <i class="fas fa-chart-line fa-3x mb-3"></i>
          <h5 class="card-title">Dashboard</h5>
          <p class="card-text">Veja gráficos detalhados de Investimentos, Metas, Gastos e Recebimentos.</p>
          <a href="dashboard?acao=exibir" class="btn btn-primary">Acessar Dashboard</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Primeira linha de cards -->
  <div class="row mt-4 text-center">
    <div class="col-md-6 mb-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <i class="fas fa-piggy-bank fa-3x mb-3"></i>
          <h5 class="card-title">Investimento</h5>
          <p class="card-text">Gerencie seus investimentos e acompanhe o crescimento do seu patrimônio.</p>
          <a href="investimento?acao=listar" class="btn btn-primary">Acessar Investimentos</a>
        </div>
      </div>
    </div>

    <div class="col-md-6 mb-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <i class="fas fa-wallet fa-3x mb-3"></i>
          <h5 class="card-title">Gastos</h5>
          <p class="card-text">Monitore seus gastos e mantenha suas finanças em ordem.</p>
          <a href="gastos?acao=listar" class="btn btn-primary">Acessar Gastos</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Segunda linha de cards -->
  <div class="row mt-4 text-center">
    <div class="col-md-6 mb-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <i class="fas fa-bullseye fa-3x mb-3"></i>
          <h5 class="card-title">Meta</h5>
          <p class="card-text">Defina e acompanhe suas metas financeiras para alcançar seus objetivos.</p>
          <a href="meta?acao=listar" class="btn btn-primary">Acessar Metas</a>
        </div>
      </div>
    </div>

    <div class="col-md-6 mb-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <i class="fas fa-hand-holding-usd fa-3x mb-3"></i>
          <h5 class="card-title">Recebimento</h5>
          <p class="card-text">Gerencie os valores recebidos e mantenha seu fluxo de caixa atualizado.</p>
          <a href="recebimento?acao=listar" class="btn btn-primary">Acessar Recebimentos</a>
        </div>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>

</body>
</html>
