package br.com.unifaj.controlebibliotecaapi.service;

import br.com.unifaj.controlebibliotecaapi.model.Emprestimo;
import br.com.unifaj.controlebibliotecaapi.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {

    private final List<Emprestimo> emprestimos =
            new ArrayList<>();

    private final LivroService livroService;

    public EmprestimoService(
            LivroService livroService
    ) {

        this.livroService = livroService;
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    public String cadastrarEmprestimo(
            Emprestimo emprestimo
    ) {

        Livro livro = livroService.buscarPorIsbn(
                emprestimo.getLivroIsbn()
        );

        if (livro == null) {
            return "Livro não encontrado";
        }

        if (!livro.isDisponivel()) {
            return "Livro indisponível";
        }

        livro.setDisponivel(false);

        emprestimos.add(emprestimo);

        return "Empréstimo realizado";
    }

    public String devolverLivro(String isbn) {

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getLivroIsbn().equals(isbn)
                    && emprestimo.getStatus().equals("EMPRESTADO")) {

                emprestimo.setStatus("DEVOLVIDO");

                livroService.devolverLivro(isbn);

                return "Livro devolvido com sucesso";
            }
        }

        return "Empréstimo não encontrado";
    }


}