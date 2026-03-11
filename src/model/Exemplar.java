package model;

import enums.StatusExemplar;

public class Exemplar {
    private String codigo;
    private Livro livro;
    private StatusExemplar status;

    // Getters e Setters

    public Exemplar(String codigo, Livro livro, StatusExemplar status) {
        this.codigo = codigo;
        this.livro = livro;
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public StatusExemplar getStatus() {
        return status;
    }

    public void setStatus(StatusExemplar status) {
        this.status = status;
    }
}
