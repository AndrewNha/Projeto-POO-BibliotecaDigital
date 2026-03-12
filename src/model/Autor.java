package model;

import java.util.ArrayList;

public class Autor extends Pessoa{

    private ArrayList<Livro> livros_escritos;
    private String nacionalidade;

    //Getters e Setters

    @Override
    public String exibirInfo() {
        return "Id: " + super.id + " / " + "Nome: " + super.nome + " / " +
                "Livros escritos: " + getLivros_escritos() +
                "Nacionalidade: " + nacionalidade;
    }

    public Autor(Integer id, String nome, ArrayList<Livro> livros_escritos) {
        super(id, nome);
        this.livros_escritos = livros_escritos;
    }

    public Autor(ArrayList<Livro> livros_escritos) {
        this.livros_escritos = livros_escritos;
    }

    public ArrayList<Livro> getLivros_escritos() {
        return livros_escritos;
    }

    public void setLivros_escritos(ArrayList<Livro> livros_escritos) {
        this.livros_escritos = livros_escritos;
    }
}
