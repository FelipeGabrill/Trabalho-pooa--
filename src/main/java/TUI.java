import java.util.Scanner;

public class TUI extends UI {

	private Scanner scanner = new Scanner(System.in);
	private UsuarioService usuarioPersistir = new UsuarioService(new UsuarioHSQL());
	private ConteudoService conteudoPersistir = new ConteudoService(new ConteudoHSQL());
	//private UsuarioList usuarioPersistir = new UsuarioList();
	//private ConteudoList conteudoPersistir = new ConteudoList();
	private boolean firstUser = true;	

	public Usuario mostrarMenuLogin() {
		
		if(firstUser) {
			System.out.println("Cadastre o primeiro usuario");
			String username = lerInfo("Digite o usuario");
			String password = lerInfo("Digite a senha");
			usuarioPersistir.save(new Usuario(0, username, password));
			firstUser = false;
		}

		while (true) {
			System.out.println("1. Login: ");
			System.out.println("2. Listar Conteúdos: ");
			System.out.println("3. Sair: ");
			System.out.print("Escolha uma opção: ");
			String opcao = scanner.next();
			scanner.nextLine();
			switch (opcao) {
			case "1":
				Usuario usuario = realizarLogin();
				return usuario;
			case "2":
				listarConteudo();
				break;
			case "3":
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}
	}

	private Usuario realizarLogin() {
		String username = lerInfo("Digite o usuario");
		String password = lerInfo("Digite a senha");
		Usuario usuario = usuarioPersistir.validarLogin(username, password);
		if (usuario != null) {
			return usuario;
		} else {
			System.out.println("Login inválido.");
			return null;
		}
	}

	private void listarConteudo() {
		for (Conteudo conteudo : conteudoPersistir.listar()) {
			System.out.println(conteudo);
		}
	}

	private String lerInfo(String label) {
		System.out.print(label + ": ");
		return scanner.nextLine().trim();
	}

	public Usuario mostrarMenuConteudo(Usuario currentUser) {
		while (true) {
			System.out.println("1. Criar Conteúdo:");
			System.out.println("2. Listar Conteúdo:");
			System.out.println("3. Atualizar Conteúdo:");
			System.out.println("4. Excluir Conteúdo:");
			System.out.println("5. Criar Usuário:");
			System.out.println("6. Listar Usuário:");
			System.out.println("7. Alterar Usuário:");
			System.out.println("8. Excluir Usuário:");
			System.out.println("9. Alterar Senha:");
			System.out.println("10. Logout:");
			System.out.print("Escolha uma opção: ");
			String opcao = scanner.next();
			scanner.nextLine();

			switch (opcao) {
			case "1":
				criarConteudo(currentUser);
				break;
			case "2":
				listarConteudo();
				break;
			case "3":
				atualizarConteudo(currentUser);
				break;
			case "4":
				excluirConteudo();
				break;
			case "5":
				criarUsuario();
				break;
			case "6":
				listaUsuario();
				break;
			case "7":
				alterarUsuario(currentUser);
				break;
			case "8":
				exclirUsuario(currentUser);
				break;
			case "9":
				alterarSenhaUsuario(currentUser);
				break;
			case "10":
				return currentUser = null;
			default:
				System.out.println("Opção inválida.");
			}
		}
	}

	private void criarConteudo(Usuario currentUser) {
		String titulo = lerInfo("Digite o Titulo");
		String texto = lerInfo("Digite o Texto");
		Conteudo conteudo = new Conteudo(null, titulo, texto, currentUser);
		conteudoPersistir.save(conteudo);
		System.out.println("Conteudo criado!");
	}

	private void atualizarConteudo(Usuario currentUser) {
		String ids = lerInfo("Digite o ID do conteudo para atualizar");
		int id = Integer.parseInt(ids);
		String titulo = lerInfo("Digite o Titulo");
		String texto = lerInfo("Digite o Texto");
		Conteudo conteudoParaAtualizar = new Conteudo(id, titulo, texto, currentUser);
		conteudoPersistir.atualizar(conteudoParaAtualizar);
		System.out.println("Conteudo Atualizado.");
	}

	private void excluirConteudo() {
		String ids = lerInfo("Digite o ID do conteudo para excluir");
		int id = Integer.parseInt(ids);
		boolean removido = conteudoPersistir.remover(id);
		if (removido) {
			System.out.println("Conteudo excluido.");
		} else {
			System.out.println("Conteudo não encontrado.");
		}
	}

	private void criarUsuario() {
		String username = lerInfo("Digite o username");
		String password = lerInfo("Digite o password");
		usuarioPersistir.save(new Usuario(0, username, password));
	}

	private void listaUsuario() {
		for (Usuario usuario : usuarioPersistir.listar()) {
			System.out.println(usuario);
		}
	}

	private void alterarUsuario(Usuario currentUser) {
		listaUsuario();
		String ids = lerInfo("Digite o ID do usuario para editar");
		int id = Integer.parseInt(ids);
		String username = lerInfo("Digite o novo username");
		String password = lerInfo("Digite a novo senha");
		Usuario usuarioParaAtualizar = new Usuario(id, username, password);
		usuarioPersistir.atualizar(usuarioParaAtualizar);
		System.out.println("Usuario Atualizado.");
	}

	private void exclirUsuario(Usuario currentUser) {
		listaUsuario();
		String ids = lerInfo("Digite o ID do usuario para editar");
		int id = Integer.parseInt(ids);
		boolean removido = usuarioPersistir.remover(id);
		if (removido) {
			System.out.println("Conteudo excluido.");
		} else {
			System.out.println("Conteudo não encontrado.");
		}
	}

	private void alterarSenhaUsuario(Usuario currentUser) {
		String novaPassword = lerInfo("Digite a nova senha");
		usuarioPersistir.atualizarSenha(currentUser.getId(), novaPassword);
		System.out.println("Senha Atualizada.");
	}
}
