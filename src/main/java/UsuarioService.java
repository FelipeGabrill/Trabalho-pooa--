import java.util.List;

public class UsuarioService {
	
	private Persistencia<Usuario> persistencia;
	
	UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	

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

	public void atualizar(int id, String username, String password) {
		persistencia.atualizar(new Usuario(id, username, password));
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
