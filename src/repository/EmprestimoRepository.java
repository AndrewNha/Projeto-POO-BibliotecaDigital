package repository;

import enums.StatusEmprestimo;
import model.Emprestimo;

import java.util.ArrayList;

public class EmprestimoRepository {

    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    // CREATE

    public void salvarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    // READ

    public ArrayList<Emprestimo> listarTodos() {
        return emprestimos;
    }

    public Emprestimo buscarPorId(Integer id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId().equals(id)) {
                return emprestimo;
            }
        }
        return null;
    }

    public ArrayList<Emprestimo> buscarPorUsuario(Integer idUsuario) {
        ArrayList<Emprestimo> emprestimosDoUsuario = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getId().equals(idUsuario)) {
                emprestimosDoUsuario.add(emprestimo);
            }
        }
        return emprestimosDoUsuario;
    }

    public ArrayList<Emprestimo> buscarPorStatus(StatusEmprestimo status) {
        ArrayList<Emprestimo> emprestimosFiltrados = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus().equals(status)) {
                emprestimosFiltrados.add(emprestimo);
            }
        }
        return emprestimosFiltrados;
    }

    // UPDATE

    public void atualizarEmprestimo(Emprestimo emprestimoAtualizado) {
        Emprestimo emprestimoDesatualizado = buscarPorId(emprestimoAtualizado.getId());

        if (emprestimoDesatualizado != null) {
            emprestimoDesatualizado.setDataDevolucao(emprestimoAtualizado.getDataDevolucao());
            emprestimoDesatualizado.setStatus(emprestimoAtualizado.getStatus());
        }

    }

    // DELETE

    public void removerEmprestimo(Integer id) {
        Emprestimo emprestimoRemovido = buscarPorId(id);

        if (emprestimoRemovido != null) {
            emprestimos.remove(emprestimoRemovido);
        }

    }
}
