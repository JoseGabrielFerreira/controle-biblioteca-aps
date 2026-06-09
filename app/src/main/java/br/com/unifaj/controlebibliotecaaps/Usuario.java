package br.com.unifaj.controlebibliotecaaps;

public class Usuario {

    private String login;
    private String senha;
    private String perfil;

    public Usuario(String login, String senha, String perfil) {
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getPerfil() {
        return perfil;
    }
}