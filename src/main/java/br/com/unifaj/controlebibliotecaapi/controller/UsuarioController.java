package br.com.unifaj.controlebibliotecaapi.controller;

import br.com.unifaj.controlebibliotecaapi.model.LoginRequest;
import br.com.unifaj.controlebibliotecaapi.model.Usuario;
import br.com.unifaj.controlebibliotecaapi.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public void cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) {

        return usuarioService.autenticar(
                request.getLogin(),
                request.getSenha()
        );
    }

}