import java.util.List;

public class UsuarioService implements Persistencia<Usuario> {

	private UsuarioHSQL usuarioHSQL = new UsuarioHSQL();
	private Persistencia<Usuario> persistencia;

	public UsuarioService(UsuarioHSQL usuarioHSQL) {
		this.usuarioHSQL = usuarioHSQL;
		this.persistencia = usuarioHSQL;
	}

	public Usuario validarLogin(String username, String password) {
		return usuarioHSQL.encontrarPorCredenciais(username, password);
	}

	@Override
	public void save(Usuario usuario) {
		persistencia.save(usuario);
	}

	@Override
	public void atualizar(Usuario usuario) {
		persistencia.atualizar(usuario);
	}

	@Override
	public List<Usuario> listar() {
		return persistencia.listar();
	}

	@Override
	public boolean remover(int id) {
		return persistencia.remover(id);
	}

	public void atualizarSenha(int id, String password) {
		usuarioHSQL.atualizarSenha(id, password);
	}

}
