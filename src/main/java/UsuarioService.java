import java.util.List;

public class UsuarioService implements Persistencia<Usuario>, PersistenciaUsuario<Usuario> {

	private Persistencia<Usuario> persistencia;
	private PersistenciaUsuario<Usuario> persistenciaUsuario;

	public UsuarioService(UsuarioHSQL usuarioHSQL) {
		this.persistencia = usuarioHSQL;
		this.persistenciaUsuario = usuarioHSQL;
	}

	@Override
	public Usuario validarLogin(String username, String password) {
		return persistenciaUsuario.validarLogin(username, password);
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

	@Override
	public void atualizarSenha(int id, String password) {
		persistenciaUsuario.atualizarSenha(id, password);
	}

}
