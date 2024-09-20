
import java.util.List;

public class ConteudoService implements Persistencia<Conteudo> {

	private Persistencia<Conteudo> persistencia;

	public ConteudoService(ConteudoHSQL conteudoHSQL) {
		this.persistencia = conteudoHSQL;
	}

	@Override
	public void save(Conteudo conteudo) {
		persistencia.save(conteudo);
	}

	@Override
	public void atualizar(Conteudo conteudo) {
		persistencia.atualizar(conteudo);
	}

	@Override
	public List<Conteudo> listar() {
		return persistencia.listar();
	}

	@Override
	public boolean remover(int id) {
		return persistencia.remover(id);
	}
}
