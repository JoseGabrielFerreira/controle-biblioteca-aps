package br.com.unifaj.controlebibliotecaaps.model;

public class LoginRequest {

    private String login;
    private String senha;

    public LoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}