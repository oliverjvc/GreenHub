<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Knjiga cveća</title>
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">


<style>
header {
	background-color: #007bff;
	color: #fff;
	padding: 15px 0;
}

section {
	margin-top: 30px;
}

.account-info {
	background-color: #f8f9fa;
	padding: 20px;
	border-radius: 8px;
}

footer {
	background-color: #007bff;
	color: #fff;
	padding: 10px 0;
	position: fixed;
	bottom: 0;
	width: 100%;
}
</style>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Knjiga cveća</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/KnjigaCveca/admin/dashboard.jsp">Početna</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/KnjigaCveca/aboutUS.jsp">O nama</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Kontakt</a>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/KnjigaCveca/MyAccount.jsp">Dobrodošli, <sec:authentication property="principal.username" /></a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/KnjigaCveca/login.jsp">Prijavljivanje</a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>


</body>

</html>
