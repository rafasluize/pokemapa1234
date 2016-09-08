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
		<form name="frmLogin" onClick="" action="post">
			<div class="row">
				<div class="form-group col-md-7">
					<label class="col-md-2">Login</label>
					<div class="col-md-5">
						<input type="text" id="login" name="login" class="form-control" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-7">
					<label class="col-md-2">Senha</label>
					<div class="col-md-5">
						<input type="password" id="senha" name="senha"
							class="form-control" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="col-md-6">
						<input type="button" value="Entrar" id="enviar" name="enviar" class="btn btn-success"/>
					</div>
					<div class="col-md-6">
						<input type="reset" value="Limpar" id="limpar" name="limpar" class="btn btn-warning" />
					</div>
				</div>
			</div>

		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
