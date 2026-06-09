package br.com.unifaj.controlebibliotecaapi.controller;

import br.com.unifaj.controlebibliotecaapi.model.Emprestimo;
import br.com.unifaj.controlebibliotecaapi.service.EmprestimoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(
            EmprestimoService emprestimoService
    ) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public List<Emprestimo> listarEmprestimos() {

        return emprestimoService
                .listarEmprestimos();
    }

    @PostMapping
    public String cadastrarEmprestimo(
            @RequestBody Emprestimo emprestimo
    ) {

        return emprestimoService
                .cadastrarEmprestimo(
                        emprestimo
                );
    }

    @PutMapping("/devolver/{isbn}")
    public String devolverLivro(
            @PathVariable String isbn
    ) {

        return emprestimoService
                .devolverLivro(isbn);
    }
}