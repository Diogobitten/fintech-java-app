<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-weight: bold;
        }
        .card {
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            border-radius: 8px;
            min-height: 500px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .card-body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
            width: 100%;
        }
        .card-title {
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 15px;
            text-align: center;
        }
        .chart-container {
            width: 100%;
            max-width: 500px;
            height: auto;
            margin: 0 auto;
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-5">
    <h2>Dashboard</h2>

    <div class="row">
        <!-- Card Gráfico de Investimentos (Linhas com data no eixo X) -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Investimentos</h3>
                    <div class="chart-container">
                        <canvas id="investimentoChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Card Gráfico de Metas (Semi-Circular) -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Metas</h3>
                    <div class="chart-container">
                        <canvas id="metaChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <!-- Card Gráfico de Gastos (Barras Horizontais) -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Gastos</h3>
                    <div class="chart-container">
                        <canvas id="gastosChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Card Gráfico de Recebimentos (Polar Area) -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Recebimentos</h3>
                    <div class="chart-container">
                        <canvas id="recebimentoChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<script src="resources/js/bootstrap.bundle.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Investimentos - Gráfico de Linhas com data no eixo X e valores no eixo Y
        const investimentoData = [
            {
                label: 'Ação',
                data: [1000, 1200, 1500],
                borderColor: 'rgba(75, 192, 192, 1)',
                fill: false,
            },
            {
                label: 'Fintech',
                data: [800, 900, 1100],
                borderColor: 'rgba(255, 99, 132, 1)',
                fill: false,
            },
            {
                label: 'Poupança',
                data: [500, 600, 750],
                borderColor: 'rgba(54, 162, 235, 1)',
                fill: false,
            }
        ];
        new Chart(document.getElementById("investimentoChart"), {
            type: 'line',
            data: {
                labels: ['Jan', 'Fev', 'Mar'],  // Exemplo de datas no eixo X
                datasets: investimentoData
            },
            options: {
                responsive: true,
                plugins: {
                    tooltip: { enabled: true },
                    legend: { display: true }
                },
                scales: {
                    x: { title: { display: true, text: 'Data' } },
                    y: { beginAtZero: true, title: { display: true, text: 'Valor Investido' } }
                }
            }
        });

        // Metas - Gráfico Semi-Circular
        new Chart(document.getElementById("metaChart"), {
            type: 'doughnut',
            data: {
                labels: ['Total Vendido', 'Meta'],
                datasets: [{
                    data: [1500, 1545],
                    backgroundColor: ['#36A2EB', '#FF9F40'],
                    borderWidth: 2,
                    cutout: '70%',
                    rotation: -90,
                    circumference: 180
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { display: false },
                    tooltip: { enabled: true }
                }
            }
        });

        // Gastos - Gráfico de Barras Horizontais com cores diferenciadas
        const gastosLabels = ['Mercado', 'Carro', 'Aniversário', 'Compra de Materiais'];
        const gastosData = [400, 700, 300, 500];
        new Chart(document.getElementById("gastosChart"), {
            type: 'bar',
            data: {
                labels: gastosLabels,
                datasets: [{
                    label: 'Valor do Gasto',
                    data: gastosData,
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0'],
                    borderWidth: 1
                }]
            },
            options: {
                indexAxis: 'y',
                scales: {
                    x: { beginAtZero: true }
                },
                plugins: {
                    tooltip: { enabled: true },
                    legend: { display: false }
                }
            }
        });

        // Recebimentos - Gráfico de Área Polar
        const recebimentoLabels = [
            <c:forEach var="recebimento" items="${recebimentos}">
            '${recebimento.descricao}',
            </c:forEach>
        ];
        const recebimentoData = [
            <c:forEach var="recebimento" items="${recebimentos}">
            ${recebimento.valorTransacao},
            </c:forEach>
        ];
        new Chart(document.getElementById("recebimentoChart"), {
            type: 'polarArea',
            data: {
                labels: recebimentoLabels,
                datasets: [{
                    data: recebimentoData,
                    backgroundColor: ['#FF9F40', '#FF6384', '#4BC0C0', '#36A2EB'],
                    hoverOffset: 6
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { display: true },
                    tooltip: { enabled: true }
                }
            }
        });
    });
</script>
</body>
</html>
