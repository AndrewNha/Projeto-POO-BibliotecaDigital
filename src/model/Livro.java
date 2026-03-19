package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Livro {
    private static Integer contadorId = 1;
    private Integer id;

    private ArrayList<Autor> autores;
    private String nome;
    private LocalDate dataPublicacao;
    private String editora;
    private String genero;
    // quantos generos existem ? .. ;-; '-'

    // Getters e Setters

    public void exibirAutores() {
        System.out.println("Autor(es): ");
        for (Autor autor : autores) {
            System.out.println(autor.exibirInfo());
        }
    }

    public Livro(ArrayList<Autor> autores, String nome, LocalDate dataPublicacao, String editora) {
        this.id = contadorId++;
        this.autores = autores;
        this.nome = nome;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
