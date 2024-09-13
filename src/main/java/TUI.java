import java.util.Scanner;

public class TUI extends UI {

	private Scanner scanner = new Scanner(System.in);
	private UsuarioService usuarioService = new UsuarioService();
	private ConteudoService conteudoService = new ConteudoService(new ConteudoHSQL());
	private boolean escolhaCadastro = true;

	public Usuario mostrarMenuLogin() {
		while (escolhaCadastro) {
		    System.out.println("Deseja fazer cadastro (y/n)? ");
		    String escolha = scanner.next();

		    if (escolha.equals("y")) {
		        System.out.println("Digite o username: ");
		        String username = scanner.next();
		        System.out.println("Digite o password: ");
		        String password = scanner.next();
		        usuarioService.save(new Usuario(0, username, password));
		        escolhaCadastro = false;

		    } else if (escolha.equals("n")) {
		        escolhaCadastro = false;
		    } else {
		        System.out.println("Opção inválida. Por favor, digite 'y' ou 'n'.");
		    }
		}

		while (true) {
			System.out.println("1. Login:");
			System.out.println("2. Listar Conteúdos:");
			System.out.println("3. Sair:");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();
			switch (opcao) {
			case 1:
				return realizarLogin();
			case 2:
				listarConteudo();
				break;
			case 3:
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}
	}

	private Usuario realizarLogin() {
		System.out.print("Digite o username: ");
		String username = scanner.nextLine();
		System.out.print("Digite o password: ");
		String password = scanner.nextLine();
		Usuario usuario = usuarioService.validarLogin(username, password);
		if (usuario != null) {
			mostrarMenuConteudo(usuario);
		}
		System.out.println("Login inválido.");
		return null;
	}

	private void listarConteudo() {
		for (Conteudo conteudo : conteudoService.listarConteudos()) {
			System.out.println(conteudo);
		}
	}

	private String lerInfo(String label) {
		System.out.print(label + ": ");
		return scanner.nextLine().trim();
	}

	public Usuario mostrarMenuConteudo(Usuario currentUser) {
		boolean continuarNoMenu = true;
		while (continuarNoMenu) {
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
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				criarConteudo(currentUser);
				break;
			case 2:
				listarConteudo();
				break;
			case 3:
				atualizarConteudo(currentUser);
				break;
			case 4:
				excluirConteudo();
				break;
			case 5:
				criarUsuario();
				break;
			case 6:
				listaUsuario();
				break;
			case 7:
				alterarUsuario(currentUser);
				break;
			case 8:
				exclirUsuario(currentUser);
				continuarNoMenu = false;
				break;
			case 9:
				alterarSenhaUsuario(currentUser);
				break;
			case 10:
				continuarNoMenu = false;
				mostrarMenuLogin();
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}
		return currentUser;
	}	
	
	private void criarConteudo(Usuario currentUser) {
		String titulo = lerInfo("Digite o Titulo");
		String texto = lerInfo("Digite o Texto");
		Conteudo conteudo = new Conteudo(null, titulo, texto, currentUser);
		conteudoService.save(conteudo);
		System.out.println("Conteudo criado!");
	}

	private void atualizarConteudo(Usuario currentUser) {
		String ids = lerInfo("Digite o ID do conteudo para atualizar");
		int id = Integer.parseInt(ids);
		String titulo = lerInfo("Digite o Titulo");
		String texto = lerInfo("Digite o Texto");
		conteudoService.atualizarConteudo(id, titulo, texto, currentUser);
		System.out.println("Conteudo Atualizado.");
	}

	private void excluirConteudo() {
		String ids = lerInfo("Digite o ID do conteudo para excluir");
		int id = Integer.parseInt(ids);
		boolean removido = conteudoService.removerConteudo(id);
		if (removido) {
			System.out.println("Conteudo excluido.");
		} else {
			System.out.println("Conteudo não encontrado.");
		}
	}
	
	private void criarUsuario() {
		String username = lerInfo("Digite o username: ");
		String password = lerInfo("Digite o password: ");
		usuarioService.save(new Usuario(0, username, password));
	}
	
	private void listaUsuario() {
		for (Usuario usuario : usuarioService.listar()) {
			System.out.println(usuario);
		}
	}
	
	private void alterarUsuario(Usuario currentUser) {
		String username = lerInfo("Digite o novo username: ");
		String password = lerInfo("Digite a novo senha: ");
		usuarioService.atualizar(new Usuario(currentUser.getId(), username, password));
		System.out.println("Usuario Atualizado.");
	}
	
	private void exclirUsuario(Usuario currentUser) {
		boolean removido = usuarioService.remover(currentUser.getId());
		if (removido) {
			System.out.println("Conteudo excluido.");
		} else {
			System.out.println("Conteudo não encontrado.");
		}
	}
	
	private void alterarSenhaUsuario(Usuario currentUser) {		
		String novaPassword = lerInfo("Digite a nova senha: ");
		usuarioService.atualizarSenha(currentUser.getId(), novaPassword);
		System.out.println("Senha Atualizada.");
	}
	
}
