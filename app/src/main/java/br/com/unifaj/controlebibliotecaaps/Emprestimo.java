package br.com.unifaj.controlebibliotecaaps;


public class Emprestimo {

    private String clienteCpf;
    private String livroIsbn;
    private String dataEmprestimo;
    private String dataDevolucao;
    private String status;

    public Emprestimo() {
    }

    public Emprestimo(
            String clienteCpf,
            String livroIsbn,
            String dataEmprestimo,
            String dataDevolucao,
            String status
    ) {
        this.clienteCpf = clienteCpf;
        this.livroIsbn = livroIsbn;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public String getLivroIsbn() {
        return livroIsbn;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return "CPF: " + clienteCpf +
                "\nISBN: " + livroIsbn +
                "\nStatus: " + status;
    }
}