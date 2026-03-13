package repository;

import enums.StatusExemplar;
import model.Exemplar;
import model.Livro;
import java.util.ArrayList;

public class ExemplarRepository {

    private ArrayList<Exemplar> exemplares = new ArrayList<>();

    // CREATE

    public void salvarExemplar(Exemplar exemplar) {
        exemplares.add(exemplar);
    }

    // READ

    public ArrayList<Exemplar> listarTodos() {
        return new ArrayList<>(exemplares);
    }

    public Exemplar buscarPorCodigo(String codigo) {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getCodigo().equalsIgnoreCase(codigo)) {
                return exemplar;
            }
        }
        return null;
    }

    public ArrayList<Exemplar> listarPorLivro(Livro livro) {
        ArrayList<Exemplar> resultado = new ArrayList<>();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getLivro().getId().equals(livro.getId())) {
                resultado.add(exemplar);
            }
        }
        return resultado;
    }

    public ArrayList<Exemplar> listarPorStatus(StatusExemplar status) {
        ArrayList<Exemplar> resultado = new ArrayList<>();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getStatus() == status) {
                resultado.add(exemplar);
            }
        }
        return resultado;
    }

    public Exemplar buscarDisponivelPorLivro(Livro livro) {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getLivro().getId().equals(livro.getId())
                    && exemplar.getStatus() == StatusExemplar.DISPONIVEL) {
                return exemplar;
            }
        }
        return null;
    }

    // UPDATE

    public void atualizarExemplar(Exemplar exemplarAtualizado) {
        Exemplar exemplarAntigo = buscarPorCodigo(exemplarAtualizado.getCodigo());

        if (exemplarAntigo != null) {
            exemplarAntigo.setLivro(exemplarAtualizado.getLivro());
            exemplarAntigo.setStatus(exemplarAtualizado.getStatus());
        }
    }

    // DELETE

    public void removerExemplar(String codigo) {
        Exemplar exemplar = buscarPorCodigo(codigo);

        if (exemplar != null) {
            exemplares.remove(exemplar);
        }
    }
}