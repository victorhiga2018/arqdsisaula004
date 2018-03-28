<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    
    <c:import url="Menu.jsp" />
    
    
	<div class="container" style="margin-top: 100px;">
	<div class="row">
		<form action="chamado_criar" method="post">
            <div class="row">
                <div class="form-group col-sm-4">
                	<label for="chamado_descricao">Descrição</label>
                	<input class="form-control" type="text" name="descricao" id="chamado_descricao" required>
 				</div>
 				</div>
 				<div class="row">
 				<div class="form-group col-sm-4">
                    <label for="fila">Escolha a Fila:</label>
                    <select class="form-control" name="idFila">
                        <c:forEach var="fila" items="${filas}">
                            <option value="${fila.id}">${fila.nome}</option>
                        </c:forEach>
                    </select> 
                </div>
            </div>
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" >Criar Chamado</button>
                    <a href="index" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
	</div>
	</div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>