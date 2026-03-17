package main;

import model.Autor;
import model.Livro;
import model.Pessoa;
import model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.of(2026, 12, 04));

        Pessoa pessoa = new Autor(1, "Raiam Santos", null);
        Pessoa pessoa1 = new Usuario(2, "Andre Bala Tensa", "andre@gmail.com", "(83)98790-6016", null);

        ArrayList<Autor> autores = new ArrayList<>();
        autores.add((Autor) pessoa);
        System.out.println(autores);
        Livro livro = new Livro(autores, "Biblia Sagrada", LocalDate.of(0000, 01, 01), "Kalunga");

        System.out.println(pessoa.exibirInfo());
        System.out.println(pessoa1.exibirInfo());
        System.out.println(livro.getNome()+" : ");
        livro.exibirAutores();
    }

}

//oieeer
