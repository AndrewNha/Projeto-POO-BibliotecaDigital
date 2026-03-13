package repository;

import model.Livro;
import java.util.ArrayList;

public class LivroRepository {

    private ArrayList<Livro> livros = new ArrayList<>();

    // CREATE

    public void salvarLivro(Livro livro) {
        livros.add(livro);
    }

    // READ

    public ArrayList<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }

    public Livro buscarPorId(Integer id) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }

    public Livro buscarPorNome(String nome) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome)) {
                return livro;
            }
        }
        return null;
    }

    public ArrayList<Livro> listarPorGenero(String genero) {
        ArrayList<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getGenero() != null && livro.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    public ArrayList<Livro> listarPorEditora(String editora) {
        ArrayList<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getEditora().equalsIgnoreCase(editora)) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    // UPDATE

    public void atualizarLivro(Livro livroAtualizado) {
        Livro livroAntigo = buscarPorId(livroAtualizado.getId());

        if (livroAntigo != null) {
            livroAntigo.setNome(livroAtualizado.getNome());
            livroAntigo.setAutores(livroAtualizado.getAutores());
            livroAntigo.setEditora(livroAtualizado.getEditora());
            livroAntigo.setGenero(livroAtualizado.getGenero());
            livroAntigo.setDataPublicacao(livroAtualizado.getDataPublicacao());
        }
    }

    // DELETE

    public void removerLivro(Integer id) {
        Livro livro = buscarPorId(id);

        if (livro != null) {
            livros.remove(livro);
        }
    }
}