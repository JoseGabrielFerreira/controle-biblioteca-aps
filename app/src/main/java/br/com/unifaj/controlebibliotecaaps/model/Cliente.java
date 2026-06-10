package br.com.unifaj.controlebibliotecaaps.model;

public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String login;
    private String senha;

    public Cliente(
            String nome,
            String cpf,
            String telefone,
            String email,
            String login,
            String senha
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.login = login;
        this.senha = senha;
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

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}