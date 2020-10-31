<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="cc.ppi.jdbc.modelo.*"%>
<%@page import="cc.ppi.jdbc.dao.*"%>
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
		<h1>Lista de Contatos</h1>
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
			int contadorContatos = 0;
		%>

		Pesquise por nome ou telefone:
		<form action="buscaContato" method="post">
			<input type="text" name="chave" /> <input type="submit"
				value="Buscar" />
		</form>

		<p />
		<table border=1 cellspacing=0 cellpadding=5>
			<tr align="center">
				<td><b>Nome</b></td>
				<td><b>E-mail</b></td>
				<td><b>Endereço</b></td>
				<td><b>Nascimento</b></td>
				<td><b>Telefone</b></td>
				<td colspan="2"><b>Opções</b></td>
			</tr>
			<%
				ContatoDao dao = new ContatoDao();
				List<Contato> contatos = dao.getLista();

				for (Contato contato : contatos) {
					String data = "";
					if (contato.getDataNascimento()!=null) {
						data = new SimpleDateFormat("dd/MM/yyyy").format(contato.getDataNascimento().getTime());
					}
			%>
			<tr>
				<td><%=contato.getNome()%></td>
				<td><%=contato.getEmail()%></td>
				<td><%=contato.getEndereco()%></td>
				<td><%=data%></td>
				<td><%=contato.getTelefone()%></td>
				<td><form action="buscaPorId" method="post">
						<input type="hidden" name="id" value="<%=contato.getId()%>">
						<input type="submit" value="Alterar" />
					</form></td>
				<td><form action="removeContato" method="post">
						<input type="hidden" name="id" value="<%=contato.getId()%>">
						<input type="submit" value="Deletar" />
					</form></td>
			</tr>
			<%
				contadorContatos++;
				}
			%>
		</table>
		<p />
		Quantidade de contatos encontrados:
		<%=contadorContatos%>
	</div>
</body>
</html>