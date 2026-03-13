package repository;

import enums.StatusEmprestimo;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import java.util.ArrayList;

public class EmprestimoRepository {

    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private Integer proximoId = 1;

    // CREATE

    public void salvarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setId(proximoId++);
        emprestimos.add(emprestimo);
    }

    // READ

    public ArrayList<Emprestimo> listarTodos() {
        return new ArrayList<>(emprestimos);
    }

    public Emprestimo buscarPorId(Integer id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId().equals(id)) {
                return emprestimo;
            }
        }
        return null;
    }

    public ArrayList<Emprestimo> listarPorUsuario(Usuario usuario) {
        ArrayList<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getId().equals(usuario.getId())) {
                resultado.add(emprestimo);
            }
        }
        return resultado;
    }

    public ArrayList<Emprestimo> listarPorLivro(Livro livro) {
        ArrayList<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getId().equals(livro.getId())) {
                resultado.add(emprestimo);
            }
        }
        return resultado;
    }

    public ArrayList<Emprestimo> listarPorStatus(StatusEmprestimo status) {
        ArrayList<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus() == status) {
                resultado.add(emprestimo);
            }
        }
        return resultado;
    }

    // UPDATE

    public void atualizarEmprestimo(Emprestimo emprestimoAtualizado) {
        Emprestimo emprestimoAntigo = buscarPorId(emprestimoAtualizado.getId());

        if (emprestimoAntigo != null) {
            emprestimoAntigo.setUsuario(emprestimoAtualizado.getUsuario());
            emprestimoAntigo.setLivro(emprestimoAtualizado.getLivro());
            emprestimoAntigo.setDataEmprestimo(emprestimoAtualizado.getDataEmprestimo());
            emprestimoAntigo.setDataDevolucao(emprestimoAtualizado.getDataDevolucao());
            emprestimoAntigo.setStatus(emprestimoAtualizado.getStatus());
        }
    }

    // DELETE

    public void removerEmprestimo(Integer id) {
        Emprestimo emprestimo = buscarPorId(id);

        if (emprestimo != null) {
            emprestimos.remove(emprestimo);
        }
    }
}