package br.com.unifaj.controlebibliotecaapi.model;

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

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getLivroIsbn() {
        return livroIsbn;
    }

    public void setLivroIsbn(String livroIsbn) {
        this.livroIsbn = livroIsbn;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}