package br.com.unifaj.controlebibliotecaapi.controller;

import br.com.unifaj.controlebibliotecaapi.model.Livro;
import br.com.unifaj.controlebibliotecaapi.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(
            LivroService livroService
    ) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    @PostMapping
    public void cadastrarLivro(
            @RequestBody Livro livro
    ) {
        livroService.cadastrarLivro(livro);
    }

    @PutMapping("/{isbn}")
    public String atualizarLivro(
            @PathVariable String isbn,
            @RequestBody Livro livro
    ) {

        return livroService.atualizarLivro(
                isbn,
                livro
        );
    }

    @DeleteMapping("/{isbn}")
    public String excluirLivro(
            @PathVariable String isbn
    ) {

        return livroService.excluirLivro(isbn);
    }
}