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

@WebServlet("/buscaPorId")
public class BuscaPorIdServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// busca o writer
		PrintWriter out = response.getWriter();
		// buscando os parâmetros no request
		String id = request.getParameter("id");

		ContatoDao dao = new ContatoDao();
		Contato contato = dao.getBuscaId(id);
		request.setAttribute("contato", contato);
		RequestDispatcher rd = request.getRequestDispatcher("/altera.jsp");
		rd.forward(request, response);

	}
}
