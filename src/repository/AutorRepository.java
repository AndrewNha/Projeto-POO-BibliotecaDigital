package repository;

import model.Autor;

import java.util.ArrayList;

public class AutorRepository {
    ArrayList<Autor> autores = new ArrayList<>();

    // CREATE, READ, UPDATE, DELETE

    // CREATE



    // READ

    public Autor buscarPorId(Integer id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        System.out.println("Autor não encontrado.");
        return null;
    }

    public Autor buscarPorNome(String nome) {
        for (Autor autor : autores) {
            if (autor.getNome().toLowerCase() == nome.toLowerCase()) {
                return autor;
            }
        }
        System.out.println("Autor não encontrado.");
        return null;
    }

    public ArrayList<Autor> listarPorNacionalidade(String nacionalidade) {
        ArrayList<Autor> autoresPorNacionalidade = new ArrayList<>();
        for (Autor autor : this.autores) {
            if (autor.getNacionalidade().toLowerCase() == nacionalidade.toLowerCase()) {
                autoresPorNacionalidade.add(autor);
            }
        }
        if (autoresPorNacionalidade != null) {
            System.out.println("Nacionalidade do(s) autor(es): " + nacionalidade.toLowerCase());
            return autoresPorNacionalidade;
        } else {
            System.out.println("Nenhum autor com essa nacionalidade foi encontrado");
            return null;
        }
    }

    // DELETE

    public void removerAutor(Integer id) {
        Autor autorRemovido = buscarPorId(id);

        if (autorRemovido != null) {
            autores.remove(autorRemovido);
            System.out.println("Autor removido com sucesso.");
        } else {
            System.out.println("O autor não pôde ser encontrado.");
        }

    }


}
