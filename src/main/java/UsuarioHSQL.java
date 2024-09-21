import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioHSQL implements Persistencia<Usuario>, PersistenciaUsuario<Usuario> {

	// memoria
	private static final String DB_URL = "jdbc:hsqldb:mem:conteudoDB";
	// arquivo
	// private static final String DB_URL = "jdbc:hsqldb:file:database/conteudoDB";
	// servidor
	// private static final String DB_URL =
	// "jdbc:hsqldb:hsql://localhost/conteudoDB";
	// http server
	// private static final String DB_URL =
	// "jdbc:hsqldb:http://localhost/conteudoDB";

	private static final String USER = "sa";
	private static final String PASSWORD = "";
	private Connection connection = null;

	public UsuarioHSQL() {
		criarTabela();
	}

	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		}
		return connection;
	}

	public void criarTabela() {
		String sql = "CREATE TABLE IF NOT EXISTS Usuario (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "username VARCHAR(255), " + "password VARCHAR(255))";

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(Usuario usuario) {
		String sql = "INSERT INTO Usuario (username, password) VALUES (?, ?)";

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getPassword());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM Usuario";

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public void atualizar(Usuario usuario) {
		String sql = "UPDATE Usuario SET username = ?, password = ? WHERE id = ?";

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getPassword());
			stmt.setInt(3, usuario.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizarSenha(int id, String password) {
		String sql = "UPDATE Usuario SET password = ? WHERE id = ?";

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean remover(int id) {
		String sql = "DELETE FROM Usuario WHERE id = ?";
		boolean delete = false;
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			delete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delete;
	}

	public Usuario validarLogin(String username, String password) {
		Usuario usuario = null;

		String sql = "SELECT * FROM Usuario WHERE username = ? AND password = ?";

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Integer id = rs.getInt("id");
					usuario = new Usuario(id, rs.getString("username"), rs.getString("password"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}
}
