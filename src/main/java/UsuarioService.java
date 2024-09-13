import java.util.List;

public class UsuarioService {
	
	UsuarioHSQL usuarioHSQL = new UsuarioHSQL();
	
	private Persistencia<Usuario> persistencia;
	
	public Usuario validarLogin(String username, String password) {
        return usuarioHSQL.encontrarPorCredenciais(username, password);
    }
    
	public void save(Usuario usuario) {
		persistencia.save(usuario);
	}

	public void atualizar(Usuario usuario) {
		persistencia.atualizar(usuario);
	}
	
	public List<Usuario> listar() {
		return persistencia.listar();
	}

	public boolean remover(int id) {
		return persistencia.remover(id);
	}
	
	public void atualizarSenha(int id, String password) {
		usuarioHSQL.atualizarSenha(id, password);
	}

}
