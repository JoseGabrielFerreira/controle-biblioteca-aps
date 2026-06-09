package br.com.unifaj.controlebibliotecaapi.service;

import br.com.unifaj.controlebibliotecaapi.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {

        usuarios.add(
                new Usuario(
                        "Administrador",
                        "admin",
                        "123",
                        "ADMIN"
                )
        );
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario autenticar(String login, String senha) {

        for (Usuario usuario : usuarios) {

            if (usuario.getLogin().equals(login)
                    && usuario.getSenha().equals(senha)) {

                return usuario;
            }
        }

        return null;
    }
}