<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Register</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>

<body>
	<header class="loginhead bg-dark">
		<div>
			<h1 class="display-4">Welcome to HappyHours!</h1>
			<span>Happy hours makes you happy like it or not. It&apos;s like cocaine.</span>
		</div>
	</header>
	<main class="loginmain">
	    <section id="login-title">
    			<h2><a href="#">Register</a> or <a href="login">Login</a> to start!</h2>
    		</section>
		<section class="card border-primary mb-3" id="login">
				<h3 class="card-header text-white bg-primary">
					Register
				</h3>
				<form:form class="card-body" modelAttribute="registerRequest" action="register">
				    <div class="input-group mb-3">
                      <form:input path="name" class="form-control" type="text" placeholder="Name"/>
                    </div>
					<div class="input-group mb-3">
					  <form:input path="email" class="form-control" type="email" placeholder="E-mail"/>
					</div>
					<div class="input-group mb-3">
					  <form:input path="password" class="form-control" type="password" placeholder="Password"/>
					</div>
					<form:button type="submit" class="btn btn-primary">Register</form:button>
				</form:form>
		</section>
	</main>
</body>
</html>