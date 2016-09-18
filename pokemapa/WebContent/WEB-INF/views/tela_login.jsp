<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Alterar Local</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="./menu.jsp" />
	<div class="container">
		<div>
			<h2 class="page-header">Entrar no sistema</h2>
			<form name="frmLogin" method="POST" action="fazer_login">
				<div class="row">
					<div class="form-group col-md-7">
						<label class="col-md-2">Login</label>
						<div class="col-md-5">
							<input type="text" id="username" name="username"
								class="form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-7">
						<label class="col-md-2">Senha</label>
						<div class="col-md-5">
							<input type="password" id="password" name="password"
								class="form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<input type="submit" value="Entrar" id="enviar" name="enviar"
							class="btn btn-success" /> <input type="reset" value="Limpar"
							id="limpar" name="limpar" class="btn btn-warning" />
					</div>
				</div>

			</form>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
