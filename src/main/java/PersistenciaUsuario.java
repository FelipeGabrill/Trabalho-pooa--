
public interface PersistenciaUsuario<T> {

	T validarLogin(String username, String password);
	
	void atualizarSenha(int id, String password);
}
