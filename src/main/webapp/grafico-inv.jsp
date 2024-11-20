<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<div class="container">
  <div class="mt-5 ms-5 me-5">

    <div class="card mb-3">
      <div class="card-header">
        GRÁFICO DE INVESTIMENTO
      </div>
      <div class="card-body">
        <h5 class="card-title">Gestão de investimentos eficiente</h5>
        <p class="card-text">Data do Investimento x Valor do Investimento.</p>
        <div class="chart-container">
          <canvas id="investmentChart"></canvas>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  var labels = [];
  var data = [];

  // Itera sobre a lista de investimentos e gera os dados para o gráfico
  <c:forEach items="${investimento}" var="investimentos">
  labels.push((new Date("${investimentos.dataInvestimento}")).toLocaleDateString('pt-BR', { day: '2-digit', month: 'short', year: 'numeric' }));
  data.push(${investimentos.valorInvestimento});
  </c:forEach>

  // Dados para o gráfico
  var investmentData = {
    labels: labels,
    datasets: [{
      label: "Valor do Investimento ao Longo do Tempo",
      data: data,
      fill: false,
      borderColor: 'rgba(75, 192, 192, 1)',
      tension: 0.1
    }]
  };

  // Configuração do gráfico
  var config = {
    type: 'line',
    data: investmentData,
    options: {
      responsive: true,
      plugins: {
        legend: {
          display: true,
          position: 'top'
        }
      },
      scales: {
        x: {
          beginAtZero: true
        },
        y: {
          beginAtZero: true
        }
      }
    }
  };

  // Renderizando o gráfico
  var investmentChart = new Chart(
          document.getElementById('investmentChart'),
          config
  );
</script>

