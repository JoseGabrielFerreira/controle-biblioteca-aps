package br.com.unifaj.controlebibliotecaaps;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private int paginas;
    private boolean disponivel;

    public Livro() {
    }

    public Livro(
            String titulo,
            String autor,
            String isbn,
            String genero,
            int paginas,
            boolean disponivel
    ) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.paginas = paginas;
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenero() {
        return genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {

        String status =
                disponivel
                        ? "Disponível"
                        : "Emprestado";

        return titulo +
                " - " +
                autor +
                " (" +
                status +
                ")";
    }
}