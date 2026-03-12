package repository;

import model.Usuario;
import java.util.ArrayList;

public class UsuarioRepository {
    ArrayList<Usuario> usuarios = new ArrayList<>();

    // CREATE, READ, UPDATE, DELETE

    // CREATE

    // READ

    public Usuario buscarPorId(Integer id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        System.out.println("Autor não encontrado.");
        return null;
    }

    public Usuario buscarPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase() == nome.toLowerCase()) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado.");
        return null;
    }

    public Usuario buscarPorTelefone(String telefone) {
        for (Usuario usuario : usuarios) {
            if (usuario.getTelefone() == telefone) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado.");
        return null;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail() == email) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado.");
        return null;
    }

    // DELETE

    public void removerUsuario(Integer id) {
        Usuario usuarioRemovido = buscarPorId(id);

        if (usuarioRemovido != null) {
            usuarios.remove(usuarioRemovido);
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("O usuário não pôde ser encontrado.");
        }

    }
}
