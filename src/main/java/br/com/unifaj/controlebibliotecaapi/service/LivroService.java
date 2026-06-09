package br.com.unifaj.controlebibliotecaapi.service;

import br.com.unifaj.controlebibliotecaapi.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    private final List<Livro> livros = new ArrayList<>();

    public LivroService() {

        livros.add(
                new Livro(
                        "Dom Casmurro",
                        "Machado de Assis",
                        "123456",
                        "Romance",
                        256,
                        true
                )
        );
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarPorIsbn(String isbn) {

        for (Livro livro : livros) {

            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }

        return null;
    }

    public boolean devolverLivro(String isbn) {

        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return false;
        }

        livro.setDisponivel(true);

        return true;
    }

    public String atualizarLivro(
            String isbn,
            Livro livroAtualizado
    ) {

        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return "Livro não encontrado";
        }

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setGenero(livroAtualizado.getGenero());
        livro.setPaginas(livroAtualizado.getPaginas());

        return "Livro atualizado com sucesso";
    }

    public String excluirLivro(String isbn) {

        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return "Livro não encontrado";
        }

        livros.remove(livro);

        return "Livro removido com sucesso";
    }

}