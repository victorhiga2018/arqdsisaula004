<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<style>
	th{
	text-align: center;
	}
</style>
</head>

<body>

	<c:import url="Menu.jsp" />


	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<h3>Chamados: ${fila.nome}</h3>
			<hr>
			<div class="form-group col-sm-12">
				<c:if test="${!empty listaChamados}">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Descrição</th>
							<th scope="col">Status</th>
							<th scope="col">Data Abertura</th>
							<th scope="col">Data Fechamento</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach var="chamado" items="${listaChamados}"> 
						<tr>
							<th scope="row"> <fmt:formatNumber pattern="0000" value="${chamado.id}"/>  </th>
							<td>${chamado.descricao}</td>
							<td>${chamado.status}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${chamado.dataAbertura}" /></td>
							<c:if test="${chamado.dataFechamento != null}">
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${chamado.dataFechamento}" /></td>
							</c:if>
							<c:if test="${chamado.dataFechamento == null}">
							<td> - </td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>
				<c:if test="${empty listaChamados}">
					<p>Esta lista de Chamados está vazia.</p>
					<button class="btn btn-default" onclick="document.location.href='fila_consultar';">Retornar</button>
				</c:if>
			</div>

		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>