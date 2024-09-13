import java.util.List;

public class UsuarioService {
	
	UsuarioHSQL usuarioHSQL = new UsuarioHSQL();
	
	private Persistencia<Usuario> persistencia;
	
	public Usuario validarLogin(String username, String password) {
        return usuarioHSQL.encontrarPorCredenciais(username, password);
    }
    
	public void save(Usuario usuario) {
		usuarioHSQL.save(usuario);
	}

	public void atualizar(Usuario usuario) {
		usuarioHSQL.atualizar(usuario);
	}
	
	public List<Usuario> listar() {
		return usuarioHSQL.listar();
	}

	public boolean remover(int id) {
		return usuarioHSQL.remover(id);
	}
	
	public void atualizarSenha(int id, String password) {
		usuarioHSQL.atualizarSenha(id, password);
	}

}
