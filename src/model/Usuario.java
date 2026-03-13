package model;

import java.util.ArrayList;

public class Usuario extends Pessoa{
    private String email;
    private String telefone;
    private ArrayList<Livro> livrosEmprestados;

    // Getters e Setters

    @Override
    public String exibirInfo() {
        return "Id: " + super.id + " / " + "Nome: " + super.nome + " / " +
                "Email: " + this.email + " / " +  "Telefone: " + telefone +
                " / " + "Livros emprestados: " + getLivrosEmprestados();
    }

    public Usuario(Integer id, String nome, String email, String telefone, ArrayList<Livro> livrosEmprestados) {
        super(id, nome);
        this.email = email;
        this.telefone = telefone;
        this.livrosEmprestados = livrosEmprestados;
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

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }
}
