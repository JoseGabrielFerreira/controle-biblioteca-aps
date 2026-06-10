package br.com.unifaj.controlebibliotecaaps.model;

public class Usuario {

    private String login;
    private String senha;
    private String perfil;

    public Usuario() {
    }

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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}