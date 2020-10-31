<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="cc.ppi.jdbc.modelo.*"%>
<%@page import="cc.ppi.jdbc.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<img src="img/images.png" />
		<h1>Cadastro de Contatos</h1>
		<hr />
		<table>
			<tr>
				<td><a href="http://localhost:8080/agenda-ramon/">Início</a></td>
				<td><a href="http://localhost:8080/agenda-ramon/novo.html">Novo</a></td>
				<td><a href="http://localhost:8080/agenda-ramon/lista.jsp">Lista
						de contatos</a></td>
			</tr>
		</table>
		<hr />
	</div>

	<div align="center">
		<%
			Contato contato = (Contato) request.getAttribute("contato");
			String data = "";
			if (contato.getDataNascimento() != null) {
				data = new SimpleDateFormat("dd/MM/yyyy").format(contato.getDataNascimento().getTime());
			}
		%>
		Os campos em negrito são obrigatórios.
		<form action="alteraContato" method="post">
			<input type="hidden" name="id" value="<%=contato.getId()%>">
			<table>
				<tr>
					<td><b>Nome: </b></td>
					<td><input type="text" name="nome"
						value="<%=contato.getNome()%>" /><br /></td>
				</tr>

				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email"
						value="<%=contato.getEmail()%>" /><br /></td>
				</tr>

				<tr>
					<td><b>Endereço: </b></td>
					<td><input type="text" name="endereco"
						value="<%=contato.getEndereco()%>" /><br /></td>
				</tr>

				<tr>
					<td>Data de Nascimento: (dd/mm/aaaa)</td>
					<td><input type="text" name="dataNascimento" value="<%=data%>" /><br /></td>
				</tr>

				<tr>
					<td><b>Telefone: </b></td>
					<td><input type="text" name="telefone"
						value="<%=contato.getTelefone()%>" /><br /></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Gravar" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>