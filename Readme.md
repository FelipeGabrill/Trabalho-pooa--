# Descrição

Este projeto é uma aplicação Java que implementa uma interface de usuário baseada em texto (TUI - Text User Interface) para gerenciar usuários e conteúdos.

## Funcionalidades

- Cadastro de Usuário
- Login
- Listagem de Conteúdos
- Criação, Atualização e Exclusão de Conteúdos
- Gerenciamento de Usuários
- Alteração de Senha

### Tipos de Persistência

A aplicação suporta dois tipos de persistência de dados:

HSQL (HyperSQL Database):
Utiliza um banco de dados relacional em memória que pode ser persistido em disco.
Ideal para aplicações que necessitam de um sistema de gerenciamento de banco de dados mais robusto, permitindo consultas SQL e suporte a transações.
Para utilizar esta forma de persistência, as seguintes linhas devem ser utilizadas na classe TUI:


```java
  private UsuarioService usuarioPersistir = new UsuarioService(new UsuarioHSQL());
  private ConteudoService conteudoPersistir = new ConteudoService(new ConteudoHSQL());
  // private UsuarioList usuarioPersistir = new UsuarioList();
  // private ConteudoList conteudoPersistir = new ConteudoList();
}
```

Memória (In-Memory):
Utiliza estruturas de dados em memória para armazenar usuários e conteúdos.
É uma solução mais simples e rápida, adequada para testes ou aplicações que não exigem persistência de dados entre execuções.
Para utilizar esta forma de persistência, as seguintes linhas devem ser utilizadas na classe TUI:

```java
  //private UsuarioService usuarioPersistir = new UsuarioService(new UsuarioHSQL());
  //private ConteudoService conteudoPersistir = new ConteudoService(new ConteudoHSQL());
  private UsuarioList usuarioPersistir = new UsuarioList();
  private ConteudoList conteudoPersistir = new ConteudoList();
}
```

### Métodos Principais

- Menu Inicial (acessível ao público)
- Login: Permite ao usuário fazer login no sistema.
- Listar Conteúdos: Exibe a lista de conteúdos disponíveis.
- Sair: Encerra a aplicação.
- Menu Após Login (acessível apenas após autenticação)
- Criar Conteúdo: Permite criar um novo conteúdo.
- Listar Conteúdo: Exibe a lista de conteúdos.
- Atualizar Conteúdo: Permite editar um conteúdo existente.
- Excluir Conteúdo: Permite deletar um conteúdo.
- Criar Usuário: Permite criar um novo usuário.
- Listar Usuários: Exibe a lista de usuários.
- Alterar Usuário: Permite editar informações de um usuário.
- Excluir Usuário: Permite deletar um usuário.
- Alterar Senha: Permite ao usuário logado alterar sua própria senha.
- Logout: Faz logout do sistema.


### Como Executar

-- Pré-requisitos: Verifique se o Java JDK e o Maven estão instalados em seu sistema.
-- Clone o repositório: Baixe o código do projeto para sua máquina.
-- Abra o projeto em um IDE: Use um ambiente de desenvolvimento integrado como IntelliJ IDEA ou Eclipse.
-- Limpe e instale a aplicação: Utilize a funcionalidade do Maven na sua IDE para realizar um "clean" e "install".
-- Execute a aplicação.

### Aplicação dos Princípios SOLID

S (Single Responsibility Principle): Cada classe deve ter uma única responsabilidade bem definida.
O (Open/Closed Principle): O código deve ser aberto para extensão, mas fechado para modificação.
L (Liskov Substitution Principle): Objetos de uma superclasse devem poder ser substituídos por objetos de subclasses sem quebrar a aplicação.
I (Interface Segregation Principle): Muitas interfaces específicas são melhores do que uma única interface geral.
D (Dependency Inversion Principle): Depender de abstrações, não de implementações. 
