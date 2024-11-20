<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Fintech</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="dash3.jsp">Home</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Cadastrar
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="investimento?acao=listar">Investimento</a></li>
                        <li><a class="dropdown-item" href="gastos?acao=listar">Gastos</a></li>
                        <li><a class="dropdown-item" href="meta?acao=listar">Meta</a></li>
                        <li><a class="dropdown-item" href="recebimento?acao=listar">Recebimento</a></li>
                    </ul>
            </ul>


            <c:if test="${not empty user}">
                <span class="navbar-text">
                    ${user}
                    <a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
                </span>
            </c:if>

        </div>
    </div>
</nav>