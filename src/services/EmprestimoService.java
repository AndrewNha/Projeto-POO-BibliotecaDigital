package services;

import enums.StatusEmprestimo;
import enums.StatusExemplar;
import model.Emprestimo;
import model.Exemplar;
import model.Livro;
import model.Usuario;
import repository.EmprestimoRepository;
import repository.ExemplarRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ExemplarRepository exemplarRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, ExemplarRepository exemplarRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
    }

    public Emprestimo realizarEmprestimo(Usuario usuario, Livro livro, int diasParaDevolucao) {

        // Regra de negócio: usuário deve possuir no máximo 3 empréstimos ativos.
        ArrayList<Emprestimo> emprestimosAtivos = listarEmprestimosAtivos(usuario);
        if (emprestimosAtivos.size() >= 3) {
            System.out.println("Erro: o usuário já possui 3 empréstimos ativos.");
            return null;
        }

        // Regra de negócio: deve existir um exemplar disponível do livro
        Exemplar exemplar = exemplarRepository.buscarDisponivelPorLivro(livro);
        if (exemplar == null) {
            System.out.println("Erro: não há exemplares disponíveis para o livro '" + livro.getNome() + "'.");
            return null;
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, StatusEmprestimo.ATIVO, usuario, livro);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        exemplar.setStatus(StatusExemplar.INDISPONIVEL);

        emprestimoRepository.salvarEmprestimo(emprestimo);

        System.out.println("Empréstimo realizado com sucesso! Devolução prevista: " + dataDevolucao.format(formatter));
        return emprestimo;
    }

    public void devolverLivro(Integer idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(idEmprestimo);

        if (emprestimo == null) {
            System.out.println("Erro: empréstimo não encontrado.");
            return;
        }

        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            System.out.println("Aviso: este empréstimo já foi encerrado.");
            return;
        }

        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        ArrayList<Exemplar> exemplares = exemplarRepository.listarPorLivro(emprestimo.getLivro());
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getStatus() == StatusExemplar.INDISPONIVEL) {
                exemplar.setStatus(StatusExemplar.DISPONIVEL);
                break;
            }
        }

        System.out.println("Devolução registrada com sucesso!");
    }

    public void verificarAtraso(Integer idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(idEmprestimo);

        if (emprestimo == null) {
            System.out.println("Erro: empréstimo não encontrado.");
            return;
        }

        if (emprestimo.getStatus() == StatusEmprestimo.ATIVO
                && LocalDate.now().isAfter(emprestimo.getDataDevolucao())) {
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            System.out.println("Atenção: empréstimo de ID " + idEmprestimo + " está em atraso!");
        }
    }

    public ArrayList<Emprestimo> listarEmprestimosAtivos(Usuario usuario) {
        ArrayList<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimoRepository.listarPorUsuario(usuario)) {
            if (emprestimo.getStatus() == StatusEmprestimo.ATIVO
                    || emprestimo.getStatus() == StatusEmprestimo.ATRASADO) {
                resultado.add(emprestimo);
            }
        }
        return resultado;
    }

    public ArrayList<Emprestimo> listarTodosEmAtraso() {
        return emprestimoRepository.listarPorStatus(StatusEmprestimo.ATRASADO);
    }

}
