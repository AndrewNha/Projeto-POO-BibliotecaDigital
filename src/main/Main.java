package main;

import enums.StatusExemplar;
import model.*;
import repository.*;
import services.EmprestimoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Repositories
    static AutorRepository autorRepository = new AutorRepository();
    static LivroRepository livroRepository = new LivroRepository();
    static UsuarioRepository usuarioRepository = new UsuarioRepository();
    static ExemplarRepository exemplarRepository = new ExemplarRepository();
    static EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

    // Service
    static EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, exemplarRepository);

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // limpa o buffer
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }

    // INÍCIO

    public static void main(String[] args) {
        int opcao = 1;

        while (opcao != 0) {
            System.out.println("\n=== BIBLIOTECA DIGITAL ===");
            System.out.println("1. Gerenciar Autores");
            System.out.println("2. Gerenciar Livros");
            System.out.println("3. Gerenciar Usuários");
            System.out.println("4. Gerenciar Empréstimos");
            System.out.println("0. Sair");

            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1 -> menuAutores();
                case 2 -> menuLivros();
                case 3 -> menuUsuarios();
                case 4 -> menuEmprestimos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // AUTORES

    static void menuAutores() {
        System.out.println("\n--- AUTORES ---");
        System.out.println("1. Cadastrar autor");
        System.out.println("2. Listar todos");
        System.out.println("3. Buscar por nome");
        System.out.println("4. Remover autor");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> cadastrarAutor();
            case 2 -> listarAutores();
            case 3 -> buscarAutorPorNome();
            case 4 -> removerAutor();
            default -> System.out.println("Opção inválida!");
        }
    }

    static void cadastrarAutor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        Autor autor = new Autor(nome, nacionalidade, new ArrayList<>());

        autorRepository.salvarAutor(autor);
        System.out.println("Autor cadastrado com sucesso!");
    }

    static void listarAutores() {
        ArrayList<Autor> autores = autorRepository.listarTodos();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        for (Autor autor : autores) {
            System.out.println(autor.exibirInfo());
        }
    }

    static void buscarAutorPorNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        Autor autor = autorRepository.buscarPorNome(nome);

        if (autor != null) {
            System.out.println(autor.exibirInfo());
        } else {
            System.out.println("Autor não encontrado.");
        }
    }

    static void removerAutor() {
        listarAutores();
        int id = lerInteiro("Id do autor para remover: ");
        autorRepository.removerAutor(id);
        System.out.println("Autor removido!");
    }

    // LIVROS

    static void menuLivros() {
        System.out.println("\n--- LIVROS ---");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Listar todos");
        System.out.println("3. Buscar por nome");
        System.out.println("4. Remover livro");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> cadastrarLivro();
            case 2 -> listarLivros();
            case 3 -> buscarLivroPorNome();
            case 4 -> removerLivro();
            default -> System.out.println("Opção inválida!");
        }
    }

    static void cadastrarLivro() {
        System.out.print("Nome do livro: ");
        String nome = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        LocalDate dataPublicacao = lerData("Data de publicação (dd/MM/yyyy): ");

        ArrayList<Autor> autoresDoLivro = new ArrayList<>();
        int qtd = lerInteiro("Quantos autores? ");

        for (int i = 0; i < qtd; i++) {
            System.out.print("Nome do autor " + (i + 1) + ": ");
            String nomeAutor = scanner.nextLine();
            Autor autor = autorRepository.buscarPorNome(nomeAutor);
            if (autor != null) {
                autoresDoLivro.add(autor);
            } else {
                System.out.println("Autor não encontrado. Cadastre o autor primeiro e tente novamente");
                return;
            }
        }

        Livro livro = new Livro(autoresDoLivro, nome, dataPublicacao, editora);
        livro.setGenero(genero);
        livroRepository.salvarLivro(livro);

        for (Autor a : autoresDoLivro) {
            a.getLivrosEscritos().add(livro);
        }

        Exemplar exemplar = new Exemplar(nome + "-EX" + livro.getId(), livro, StatusExemplar.DISPONIVEL);
        exemplarRepository.salvarExemplar(exemplar);

        System.out.println("Livro cadastrado com sucesso com 1 exemplar disponível!");
    }

    static void listarLivros() {
        ArrayList<Livro> livros = livroRepository.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (Livro livro : livros) {
            System.out.println("Id: " + livro.getId()
                    + " / Nome: " + livro.getNome()
                    + " / Editora: " + livro.getEditora()
                    + " / Gênero: " + livro.getGenero()
                    + " / Autores: " + livro.getAutores().stream()
                    .map(a -> a.getNome()).toList());
        }
    }

    static void buscarLivroPorNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        Livro livro = livroRepository.buscarPorNome(nome);

        if (livro != null) {
            System.out.println("Id: " + livro.getId()
                    + " / Nome: " + livro.getNome()
                    + " / Editora: " + livro.getEditora()
                    + " / Gênero: " + livro.getGenero());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    static void removerLivro() {
        listarLivros();
        int id = lerInteiro("Id do livro para remover: ");
        livroRepository.removerLivro(id);
        System.out.println("Livro removido!");
    }

    // USUÁRIOS

    static void menuUsuarios() {
        System.out.println("\n--- USUÁRIOS ---");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Listar todos");
        System.out.println("3. Buscar por nome");
        System.out.println("4. Remover usuário");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> cadastrarUsuario();
            case 2 -> listarUsuarios();
            case 3 -> buscarUsuarioPorNome();
            case 4 -> removerUsuario();
            default -> System.out.println("Opção inválida!");
        }
    }

    static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, telefone, new ArrayList<>());
        usuarioRepository.salvarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    static void listarUsuarios() {
        ArrayList<Usuario> usuarios = usuarioRepository.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.exibirInfo());
        }
    }

    static void buscarUsuarioPorNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        Usuario usuario = usuarioRepository.buscarPorNome(nome);

        if (usuario != null) {
            System.out.println(usuario.exibirInfo());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    static void removerUsuario() {
        listarUsuarios();
        int id = lerInteiro("Id do usuário para remover: ");
        usuarioRepository.removerUsuario(id);
        System.out.println("Usuário removido!");
    }

    // EMPRÉSTIMOS

    static void menuEmprestimos() {
        System.out.println("\n--- EMPRÉSTIMOS ---");
        System.out.println("1. Realizar empréstimo");
        System.out.println("2. Devolver livro");
        System.out.println("3. Listar empréstimos ativos de um usuário");
        System.out.println("4. Verificar atraso");
        System.out.println("5. Listar todos em atraso");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> realizarEmprestimo();
            case 2 -> devolverLivro();
            case 3 -> listarEmprestimosUsuario();
            case 4 -> verificarAtraso();
            case 5 -> listarEmAtraso();
            default -> System.out.println("Opção inválida!");
        }
    }

    static void realizarEmprestimo() {
        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = usuarioRepository.buscarPorNome(nomeUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Nome do livro: ");
        String nomeLivro = scanner.nextLine();
        Livro livro = livroRepository.buscarPorNome(nomeLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        int dias = lerInteiro("Prazo em dias para devolução: ");

        emprestimoService.realizarEmprestimo(usuario, livro, dias);
    }

    static void devolverLivro() {
        int id = lerInteiro("Id do empréstimo: ");
        emprestimoService.devolverLivro(id);
    }

    static void listarEmprestimosUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
        Usuario usuario = usuarioRepository.buscarPorNome(nome);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        ArrayList<Emprestimo> emprestimos = emprestimoService.listarEmprestimosAtivos(usuario);
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo.");
            return;
        }
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo.exibirInfo()
                    + " / Livro: " + emprestimo.getLivro().getNome());
        }
    }

    static void verificarAtraso() {
        int id = lerInteiro("Id do empréstimo: ");
        emprestimoService.verificarAtraso(id);
    }

    static void listarEmAtraso() {
        ArrayList<Emprestimo> atrasados = emprestimoService.listarTodosEmAtraso();
        if (atrasados.isEmpty()) {
            System.out.println("Nenhum empréstimo em atraso.");
            return;
        }
        for (Emprestimo emprestimo : atrasados) {
            System.out.println(emprestimo.exibirInfo()
                    + " / Livro: " + emprestimo.getLivro().getNome()
                    + " / Usuário: " + emprestimo.getUsuario().getNome());
        }
    }
}