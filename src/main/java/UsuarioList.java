import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioList implements Persistencia<Usuario> {

	private List<Usuario> usuarios = new ArrayList<>();
	private int count = 1;

	@Override
	public void save(Usuario usuario) {
		if (usuario.getId() == null) {
			usuario.setId(count++);
		}
		usuarios.add(usuario);
	}

	@Override
	public void atualizar(Usuario entidade) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == entidade.getId()) {
				usuario.setUsername(entidade.getUsername());
				usuario.setPassword(entidade.getPassword());
				break;
			}
		}
	}

	@Override
	public List<Usuario> listar() {
        return Collections.unmodifiableList(usuarios);
	}

	@Override
	public boolean remover(int id) {
		return usuarios.removeIf(usuario -> usuario.getId() == id);
	}
	
	public Usuario validarLogin(String username, String password) {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
	            return usuario;
	        }
	    }
	    return null; 
	}
	
	public void atualizarSenha(int id, String novaSenha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setPassword(novaSenha);   
                break;
            }
        }   
    }
}
