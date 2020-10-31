package cc.ppi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.ppi.jdbc.dao.ContatoDao;
import cc.ppi.jdbc.modelo.Contato;

@WebServlet("/alteraContato")
public class AlteraContatoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// busca o writer
		PrintWriter out = response.getWriter();
		// buscando os parâmetros no request
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		String telefone = request.getParameter("telefone");
		// fazendo a conversão da data

		if (!dataEmTexto.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
			} catch (ParseException e) {
				dataEmTexto = "";
				return; // para a execução do método
			}
		}
		// monta um objeto contato
		Contato contato = new Contato();
		contato.setId(Long.parseLong(id));
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);
		contato.setTelefone(telefone);

		// Testa se todos os campos obrigatórios foram preenchidos
		if (contato.getNome().isEmpty() || contato.getEndereco().isEmpty() || contato.getTelefone().isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/contato-invalido.jsp");
			rd.forward(request, response);
		} else {
			// salva o contato
			ContatoDao dao = new ContatoDao();
			dao.altera(contato);
			RequestDispatcher rd = request.getRequestDispatcher("/contato-adicionado.jsp");
			rd.forward(request, response);
		}

	}
}
