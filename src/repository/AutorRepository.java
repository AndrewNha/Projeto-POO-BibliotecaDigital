package repository;

import model.Autor;

import java.util.ArrayList;

public class AutorRepository {
    ArrayList<Autor> autores = new ArrayList<>();

    // CREATE, READ, UPDATE, DELETE

    // CREATE

    public void salvarAutor (Autor autor) {
        autores.add(autor);
    }

    // READ

    public ArrayList<Autor> listarTodos() {
        return autores;
    }

    public Autor buscarPorId(Integer id) {
        for (Autor autor : autores) {
            if (autor.getId().equals(id)) {
                return autor;
            }
        }
        return null;
    }

    public Autor buscarPorNome(String nome) {
        for (Autor autor : autores) {
            if (autor.getNome().equalsIgnoreCase(nome)) {
                return autor;
            }
        }
        return null;
    }

    public ArrayList<Autor> listarPorNacionalidade(String nacionalidade) {
        ArrayList<Autor> resultado = new ArrayList<>();
        for (Autor autor : this.autores) {
            if (autor.getNacionalidade().equalsIgnoreCase(nacionalidade)) {
                resultado.add(autor);
            }
        }
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    // UPDATE

    public void atualizarAutor (Autor autorAtualizado) {
        Autor autorDesatualizado = buscarPorId(autorAtualizado.getId());

        if (autorDesatualizado != null) {
            autorDesatualizado.setNome(autorAtualizado.getNome());
            autorDesatualizado.setNacionalidade(autorAtualizado.getNacionalidade());
            autorDesatualizado.setLivrosEscritos(autorAtualizado.getLivrosEscritos());
        }
    }

    // DELETE

    public void removerAutor(Integer id) {
        Autor autorRemovido = buscarPorId(id);

        if (autorRemovido != null) {
            autores.remove(autorRemovido);
        }
    }

}
