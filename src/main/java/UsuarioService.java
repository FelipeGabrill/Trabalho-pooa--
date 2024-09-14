import java.util.List;

public class UsuarioService implements Persistencia<Usuario>{
	
	UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	
	private Persistencia<Usuario> persistencia;

	public UsuarioService(UsuarioRepositorio usuarioRepositorio, Persistencia<Usuario> persistencia) {
		this.usuarioRepositorio = usuarioRepositorio;
		this.persistencia = persistencia;
	}

	public Usuario validarLogin(String username, String password) {
        return usuarioRepositorio.validarLogin(username, password);
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
		usuarioRepositorio.atualizarSenha(id, password);
	}

}
