package model;

import java.util.ArrayList;

public class Autor extends Pessoa{
    static Integer contadorId = 1;

    private ArrayList<Livro> livrosEscritos;
    private String nacionalidade;

    //Getters e Setters

    @Override
    public String exibirInfo() {
        return "Id: " + super.id + " / " + "Nome: " + super.nome + " / " +
                "Livros escritos: " + getLivrosEscritos().stream()
                .map(l -> l.getNome()).toList() + " / " +
                "Nacionalidade: " + nacionalidade;
    }

    public Autor(String nome, String nacionalidade, ArrayList<Livro> livrosEscritos) {
        super.setId(contadorId++);
        super.setNome(nome);
        this.nacionalidade = nacionalidade;
        this.livrosEscritos = livrosEscritos;
    }

    public ArrayList<Livro> getLivrosEscritos() {
        return livrosEscritos;
    }

    public void setLivrosEscritos(ArrayList<Livro> livrosEscritos) {
        this.livrosEscritos = livrosEscritos;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
