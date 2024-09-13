import java.util.List;

public class UsuarioService {
	
	UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	
	private Persistencia<Usuario> persistencia;

	public UsuarioService(UsuarioRepositorio usuarioRepositorio, Persistencia<Usuario> persistencia) {
		this.usuarioRepositorio = usuarioRepositorio;
		this.persistencia = persistencia;
	}

	public Usuario validarLogin(String username, String password) {
        return usuarioRepositorio.validarLogin(username, password);
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
		usuarioRepositorio.atualizarSenha(id, password);
	}

}
