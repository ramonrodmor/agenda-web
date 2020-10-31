package cc.ppi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.ppi.jdbc.dao.ContatoDao;
import cc.ppi.jdbc.modelo.Contato;

@WebServlet("/buscaContato")
public class BuscaContatoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// busca o writer
		PrintWriter out = response.getWriter();
		// buscando os parâmetros no request
		String chave = request.getParameter("chave");

		// Testa se todos os campos obrigatórios foram preenchidos
		if (!chave.isEmpty()) {
			ContatoDao dao = new ContatoDao();
			List<Contato> contatosEncontrados = dao.getBusca(chave);
			request.setAttribute("lista", contatosEncontrados);
			RequestDispatcher rd = request.getRequestDispatcher("/busca.jsp");
			rd.forward(request, response);
		}
	}
}
