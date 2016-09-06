<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <!DOCTYPE html>
        <html lang="pt-br">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Exibir Local</title>

            <link href="css/bootstrap.min.css" rel="stylesheet">
            <link href="css/style.css" rel="stylesheet">
        </head>

        <body>
                <!-- Modal -->
                <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="modalLabel">Excluir Local</h4>
                            </div>
                            <div class="modal-body">
                                Deseja realmente excluir este local?
                            </div>
                            <div class="modal-footer">
                                <form action="remover_local" method="post">
                                    <input type="hidden" name="id" value="${local.id }" />
                                    <button type="submit" class="btn btn-primary">Sim</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.modal -->
                <!-- Barra superior com os menus de navegação -->
				<c:import url="../menu.jsp"/>
                <!-- Container Principal -->
                <div id="main" class="container">
                    <h3 class="page-header">Exibir Local #${local.id }</h3>
                    <div class="row">
                        <div class="col-md-8">
                            <p><strong>Nome</strong>
                            </p>
                            <p>
                                ${local.nome}
                            </p>
                        </div>
                        <div class="col-md-4">
                            <p><strong>Tipo</strong>
                            </p>
                            <p>
                                ${local.nomeTipo}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <p><strong>Latitude</strong>
                            </p>
                            <p>
                                ${local.latitude}
                            </p>
                        </div>
                        <div class="col-md-4">
                            <p><strong>Longitude</strong>
                            </p>
                            <p>
                                ${local.longitude}
                            </p>
                        </div>
                        <div class="col-md-4">
                            <p><strong>Cidade</strong>
                            </p>
                            <p>
                                ${local.nomeCidade}
                            </p>
                        </div>
                    </div>
                    <hr />
                    <div id="actions" class="row">
                        <div class="col-md-12">
                            <a href="alterar_local?id=${local.id }" class="btn btn-primary">Alterar</a>
                            <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#delete-modal">Excluir</a>
                            <a href="listar_locais" class="btn btn-default">Voltar</a>
                        </div>
                    </div>
                </div>
                <script src="js/jquery.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
        </body>

        </html>