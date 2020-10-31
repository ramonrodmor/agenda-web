package cc.ppi.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import cc.ppi.jdbc.ConnectionFactory;
import cc.ppi.jdbc.modelo.Contato;

public class ContatoDao {

	// a conexão com o banco de dados
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatosTp1 " + "(nome,email,endereco,nascimento,telefone)" + " values (?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			try {
				stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			} catch (RuntimeException e) {
				stmt.setDate(4, null);
			}
			stmt.setString(5, contato.getTelefone());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatosTp1 order by nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				contato.setTelefone(rs.getString("telefone"));

				// montando a data através do Calendar
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("nascimento"));
					contato.setDataNascimento(data);
				} catch (RuntimeException e) {
					contato.setDataNascimento(null);
				}

				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getBusca(String chave) {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from contatosTp1 where nome like ? or telefone like ? order by nome");

			// // seta os valores
			stmt.setString(1, '%' + chave + '%');
			stmt.setString(2, '%' + chave + '%');
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				contato.setTelefone(rs.getString("telefone"));

				// montando a data através do Calendar
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("nascimento"));
					contato.setDataNascimento(data);
				} catch (RuntimeException e) {
					contato.setDataNascimento(null);
				}

				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Contato getBuscaId(String id) {
		try {
			Contato contato = new Contato();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatosTp1 where id like ?");

			// // seta os valores
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				contato.setTelefone(rs.getString("telefone"));

				// montando a data através do Calendar
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("nascimento"));
					contato.setDataNascimento(data);
				} catch (RuntimeException e) {
					contato.setDataNascimento(null);
				}
			}

			rs.close();
			stmt.close();
			return contato;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Contato contato) {
		String sql = "update contatosTp1 set nome=?, email=?," + "endereco=?, nascimento=?, telefone=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			try {
				stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			} catch (RuntimeException e) {
				stmt.setDate(4, null);
			}
			stmt.setString(5, contato.getTelefone());
			stmt.setLong(6, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from contatosTp1 where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}