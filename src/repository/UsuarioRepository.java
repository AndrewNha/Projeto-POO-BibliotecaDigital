package repository;

import model.Autor;
import model.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsuarioRepository {
    ArrayList<Usuario> usuarios = new ArrayList<>();

    // CREATE, READ, UPDATE, DELETE

    // CREATE

    public void salvarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // READ

    public ArrayList<Usuario> listarTodos() {
        return usuarios;
    }

    public Usuario buscarPorId(Integer id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorTelefone(String telefone) {
        for (Usuario usuario : usuarios) {
            if (usuario.getTelefone().equalsIgnoreCase(telefone)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    // UPDATE

    public void atualizaUsuario (Usuario usuarioAtualizado) {
        Usuario usuarioDesatualizado = buscarPorId(usuarioAtualizado.getId());

        if (usuarioDesatualizado != null) {
            usuarioDesatualizado.setNome(usuarioAtualizado.getNome());
            usuarioDesatualizado.setEmail(usuarioAtualizado.getEmail());
            usuarioDesatualizado.setTelefone(usuarioAtualizado.getTelefone());
            usuarioDesatualizado.setLivrosEmprestados(usuarioAtualizado.getLivrosEmprestados());
        }

    }

    // DELETE

    public void removerUsuario(Integer id) {
        Usuario usuarioRemovido = buscarPorId(id);

        if (usuarioRemovido != null) {
            usuarios.remove(usuarioRemovido);
        }

    }
}
