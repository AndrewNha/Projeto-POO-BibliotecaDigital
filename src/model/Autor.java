package model;

import java.util.ArrayList;

public class Autor extends Pessoa{

    private ArrayList<Livro> livrosEscritos;
    private String nacionalidade;

    //Getters e Setters

    @Override
    public String exibirInfo() {
        return "Id: " + super.id + " / " + "Nome: " + super.nome + " / " +
                "Livros escritos: " + getLivrosEscritos() + " / " +
                "Nacionalidade: " + nacionalidade;
    }

    public Autor(Integer id, String nome, ArrayList<Livro> livrosEscritos) {
        super(id, nome);
        this.livrosEscritos = livrosEscritos;
    }

    public Autor(ArrayList<Livro> livrosEscritos) {
        this.livrosEscritos = this.livrosEscritos;
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
