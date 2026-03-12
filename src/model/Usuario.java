package model;

import java.util.ArrayList;

public class Usuario extends Pessoa{
    private String email;
    private String telefone;
    private ArrayList<Livro> livros_emprestados;

    // Getters e Setters

    @Override
    public String exibirInfo() {
        return "Id: " + super.id + " / " + "Nome: " + super.nome + " / " +
                "Email: " + this.email + " / " +  "Telefone: " + telefone +
                " / " + "Livros emprestados: " + getLivros_emprestados();
    }

    public Usuario(Integer id, String nome, String email, String telefone, ArrayList<Livro> livros_emprestados) {
        super(id, nome);
        this.email = email;
        this.telefone = telefone;
        this.livros_emprestados = livros_emprestados;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<Livro> getLivros_emprestados() {
        return livros_emprestados;
    }

    public void setLivros_emprestados(ArrayList<Livro> livros_emprestados) {
        this.livros_emprestados = livros_emprestados;
    }
}
