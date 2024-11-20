<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Fintech</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/footer.css">
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Login</h3>
                </div>
                <div class="card-body">
                    <!-- Exibir mensagem de erro, se houver -->
                    <c:if test="${not empty erro}">
                        <div class="alert alert-danger">${erro}</div>
                    </c:if>

                    <form action="login" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Email</label>
                            <input type="text" class="form-control" id="username" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Senha</label>
                            <input type="password" class="form-control" id="password" name="senha" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Entrar</button>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <p>Não tem uma conta? <a href="cadastro-usuario.jsp">Cadastre-se</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
