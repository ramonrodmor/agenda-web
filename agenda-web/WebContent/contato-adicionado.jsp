<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<img src="img/images.png" />
		<h1>Contato Adicionado</h1>
		<hr />
		<table>
			<tr>
				<td><a href="http://localhost:8080/agenda-web/">Início</a></td>
				<td><a href="http://localhost:8080/agenda-web/novo.html">Novo</a></td>
				<td><a href="http://localhost:8080/agenda-web/lista.jsp">Lista
						de contatos</a></td>
			</tr>
		</table>
		<hr />
	</div>

	<div align="center">Contato <b>${param.nome}</b> adicionado/alterado com sucesso.</div>
</body>
</html>