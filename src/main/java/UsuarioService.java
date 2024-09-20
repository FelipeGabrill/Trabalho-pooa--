import java.util.List;

public class UsuarioService implements Persistencia<Usuario>  {
	
	private UsuarioHSQL usuarioHSQL = new UsuarioHSQL();
	
	
	public UsuarioService(UsuarioHSQL usuarioHSQL) {
		this.usuarioHSQL = usuarioHSQL;
	}

	public Usuario validarLogin(String username, String password) {
		return usuarioHSQL.encontrarPorCredenciais(username, password);
    }
    
	@Override
	public void save(Usuario usuario) {
		save(usuario);
	}

	@Override
	public void atualizar(Usuario usuario) {
		atualizar(usuario);
	}
	
	@Override
	public List<Usuario> listar() {
		return listar();
	}

	@Override
	public boolean remover(int id) {
		return remover(id);
	}
	
	public void atualizarSenha(int id, String password) {
		usuarioHSQL.atualizarSenha(id, password);
	}

}
