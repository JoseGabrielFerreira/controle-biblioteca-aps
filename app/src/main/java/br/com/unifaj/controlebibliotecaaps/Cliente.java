package br.com.unifaj.controlebibliotecaaps;

public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente() {
    }

    public Cliente(
            String nome,
            String cpf,
            String telefone,
            String email
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome + " - CPF: " + cpf;
    }
}