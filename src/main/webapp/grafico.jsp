<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Fintech - Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
  <style>
    .card {
      min-height: 250px;
      margin-bottom: 20px;
    }
    .input-group {
      margin-bottom: 10px;
    }
    .chart-container {
      height: 300px;
    }
    .recebimento-value {
      font-size: 2rem;
      font-weight: bold;
      color: #28a745;
    }
    .gasto-label {
      display: flex;
      justify-content: space-between;
    }
  </style>
</head>
<body class="bg-light">

<%@include file="header.jsp"%>

<div class="container mt-5">
  <h2 class="text-center mb-4">Dashboard</h2>

  <div class="row">
    <!-- Investimento (Gráfico de Barras Verticais) -->
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-header text-center">
          <h5>Investimento</h5>
        </div>
        <div class="card-body">
          <div class="input-group row">
            <div class="col-md-4 mb-3">
              <label>CDB:</label>
              <input type="number" id="cdbValue" class="form-control" placeholder="Valor para CDB" min="0">
            </div>
            <div class="col-md-4 mb-3">
              <label>Ações:</label>
              <input type="number" id="acoesValue" class="form-control" placeholder="Valor para Ações" min="0">
            </div>
            <div class="col-md-4 mb-3">
              <label>LCI:</label>
              <input type="number" id="lciValue" class="form-control" placeholder="Valor para LCI" min="0">
            </div>
            <button class="btn btn-primary mt-3 w-100" onclick="updateInvestimentoChart()">Atualizar Gráfico</button>
          </div>
          <div class="chart-container">
            <canvas id="investimentoChart"></canvas>
          </div>
        </div>
      </div>
    </div>

    <!-- Meta (Gráfico em Círculo) -->
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-header text-center">
          <h5>Meta</h5>
        </div>
        <div class="card-body">
          <canvas id="metaChart"></canvas>
        </div>
      </div>
    </div>

    <!-- Transação (Gráfico de Linha) -->
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-header text-center">
          <h5>Transação</h5>
        </div>
        <div class="card-body">
          <div class="input-group">
            <div class="mb-3">
              <label>Data da Transação:</label>
              <input type="date" id="transacaoData" class="form-control">
            </div>
            <div class="mb-3">
              <label>Envio de Dinheiro:</label>
              <input type="text" id="envioValues" class="form-control" placeholder="Digite os valores separados por vírgula">
            </div>
            <div class="mb-3">
              <label>Recebimento de Dinheiro:</label>
              <input type="text" id="recebimentoValues" class="form-control" placeholder="Digite os valores separados por vírgula">
            </div>
            <button class="btn btn-primary mt-3 w-100" onclick="updateTransacaoChart()">Atualizar Gráfico</button>
          </div>
          <div class="chart-container">
            <canvas id="transacaoChart"></canvas>
          </div>
        </div>
      </div>
    </div>

    <!-- Recebimento -->
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-header text-center">
          <h5>Recebimento</h5>
        </div>
        <div class="card-body">
          <div class="mb-3">
            <label>Valor do Recebimento:</label>
            <input type="number" id="recebimentoInput" class="form-control" placeholder="Digite o valor do Recebimento" min="0">
          </div>
          <button class="btn btn-primary w-100" onclick="updateRecebimento()">Atualizar Valor de Recebimento</button>
          <div class="d-flex justify-content-center align-items-center mt-4">
            <div id="recebimentoValue" class="recebimento-value">R$ 2.000,00</div>
          </div>
        </div>
      </div>
    </div>

    <!-- Gastos -->
    <div class="col-md-12">
      <div class="card shadow-sm">
        <div class="card-header text-center">
          <h5>Gastos</h5>
        </div>
        <div class="card-body">
          <div class="input-group row">
            <div class="col-md-4 mb-3">
              <label>Luz:</label>
              <input type="number" id="gastoLuz" class="form-control" placeholder="Valor da Luz" min="0">
            </div>
            <div class="col-md-4 mb-3">
              <label>Gás:</label>
              <input type="number" id="gastoGas" class="form-control" placeholder="Valor do Gás" min="0">
            </div>
            <div class="col-md-4 mb-3">
              <label>Condomínio:</label>
              <input type="number" id="gastoCondominio" class="form-control" placeholder="Valor do Condomínio" min="0">
            </div>
            <button class="btn btn-primary mt-3 w-100" onclick="updateGastosChart()">Atualizar Gráfico de Gastos</button>
          </div>
          <div class="chart-container">
            <canvas id="gastosChart"></canvas>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="resources/js/bootstrap.bundle.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
  let transacaoDatas = [];
  let investimentoChart, gastosChart;

  // Atualização do valor de Recebimento
  function updateRecebimento() {
    const valorRecebimento = document.getElementById('recebimentoInput').value || 0;
    document.getElementById('recebimentoValue').innerText = 'R$ ' + parseInt(valorRecebimento).toLocaleString();
  }

  const investimentoData = {
    labels: ['CDB', 'Ações', 'LCI'],
    datasets: [{
      label: 'Investimento (R$)',
      data: [20000, 200000, 70000],
      backgroundColor: ['#007bff', '#28a745', '#ffc107']
    }]
  };

  function renderInvestimentoChart() {
    const ctx = document.getElementById('investimentoChart').getContext('2d');
    investimentoChart = new Chart(ctx, {
      type: 'bar',
      data: investimentoData,
      options: {
        indexAxis: 'y',
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            beginAtZero: true,
            ticks: {
              callback: function(value) {
                return 'R$ ' + value.toLocaleString();
              }
            }
          }
        }
      }
    });
  }

  function updateInvestimentoChart() {
    const cdbValue = document.getElementById('cdbValue').value || 0;
    const acoesValue = document.getElementById('acoesValue').value || 0;
    const lciValue = document.getElementById('lciValue').value || 0;

    investimentoChart.data.datasets[0].data = [parseInt(cdbValue), parseInt(acoesValue), parseInt(lciValue)];
    investimentoChart.update();
  }

  renderInvestimentoChart();

  // Gráfico Circular para Meta
  const metaData = {
    labels: ['Concluído', 'Pendente'],
    datasets: [{
      data: [60, 40],
      backgroundColor: ['#4caf50', '#ff5722']
    }]
  };

  new Chart(document.getElementById('metaChart'), {
    type: 'doughnut',
    data: metaData,
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'top'
        }
      }
    }
  });

  function addTransactionDate() {
    const dateInput = document.getElementById('transacaoData');
    const date = dateInput.value;
    if (date) {
      const formattedDate = new Date(date).toLocaleDateString('pt-BR');
      transacaoDatas.push(formattedDate);
      dateInput.value = '';
    }
  }

  let transacaoChart;
  const transacaoData = {
    labels: transacaoDatas,
    datasets: [
      {
        label: 'Envio de Dinheiro',
        data: [20, 35, 40, 25, 50],
        borderColor: 'rgba(255, 99, 132, 1)',
        backgroundColor: 'rgba(255, 99, 132, 0.2)',
        borderWidth: 2
      },
      {
        label: 'Recebimento de Dinheiro',
        data: [30, 45, 35, 50, 65],
        borderColor: 'rgba(54, 162, 235, 1)',
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        borderWidth: 2
      }
    ]
  };

  function renderTransacaoChart() {
    const ctx = document.getElementById('transacaoChart').getContext('2d');
    transacaoChart = new Chart(ctx, {
      type: 'line',
      data: transacaoData,
      options: {
        responsive: true,
        maintainAspectRatio: false
      }
    });
  }

  function updateTransacaoChart() {
    addTransactionDate();
    const envioValues = document.getElementById('envioValues').value.split(',').map(Number);
    const recebimentoValues = document.getElementById('recebimentoValues').value.split(',').map(Number);

    transacaoChart.data.labels = transacaoDatas;
    transacaoChart.data.datasets[0].data = envioValues;
    transacaoChart.data.datasets[1].data = recebimentoValues;
    transacaoChart.update();
  }

  renderTransacaoChart();

  // Gráfico de Barras para Gastos
  function renderGastosChart() {
    const ctx = document.getElementById('gastosChart').getContext('2d');
    gastosChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Luz', 'Gás', 'Condomínio'],
        datasets: [{
          label: 'Gastos (R$)',
          data: [200, 150, 500],
          backgroundColor: ['#ff6384', '#36a2eb', '#ffce56']
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              callback: function(value) {
                return 'R$ ' + value.toLocaleString();
              }
            }
          }
        }
      }
    });
  }

  function updateGastosChart() {
    const luzValue = document.getElementById('gastoLuz').value || 0;
    const gasValue = document.getElementById('gastoGas').value || 0;
    const condominioValue = document.getElementById('gastoCondominio').value || 0;

    gastosChart.data.datasets[0].data = [parseInt(luzValue), parseInt(gasValue), parseInt(condominioValue)];
    gastosChart.update();
  }

  renderGastosChart();
</script>

<%@include file="footer.jsp"%>

</body>
</html>
