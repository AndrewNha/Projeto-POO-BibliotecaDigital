package model;

import enums.StatusEmprestimo;
import java.time.LocalDate;

public class Emprestimo {
    private Integer id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo = LocalDate.now();
    private LocalDate dataDevolucao;
    private StatusEmprestimo status;

    //Getters e Setters

    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao, StatusEmprestimo status, Usuario usuario, Livro livro) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
        this.usuario = usuario;
        this.livro = livro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }


}
