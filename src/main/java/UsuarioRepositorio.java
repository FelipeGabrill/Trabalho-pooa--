public class UsuarioRepositorio {
	
	UsuarioHSQL usuarioHSQL = new UsuarioHSQL();
	
	public Usuario validarLogin(String username, String password) {
        return usuarioHSQL.encontrarPorCredenciais(username, password);
    }
	
	public void atualizarSenha(int id, String password) {
		usuarioHSQL.atualizarSenha(id, password);
	}
}
