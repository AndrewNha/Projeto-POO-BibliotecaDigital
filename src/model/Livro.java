package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Livro {
    private ArrayList<Autor> autores;
    private String nome;
    private LocalDate dataPublicacao;
    private String editora;

    // Getters e Setters

    public Livro(ArrayList<Autor> autores, String nome, LocalDate dataPublicacao, String editora) {
        this.autores = autores;
        this.nome = nome;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
