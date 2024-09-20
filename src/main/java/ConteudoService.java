
import java.util.List;

public class ConteudoService implements Persistencia<Conteudo>{

	public ConteudoService() {		
	}

    @Override
	public void save(Conteudo conteudo) {
		save(conteudo);
    }
    
    @Override
	public void atualizar(Conteudo conteudo) {
		atualizar(conteudo);
    }

    @Override
	public List<Conteudo> listar() {
		return listar();
    }

    @Override
	public boolean remover(int id) {
		return remover(id);
    }
}
