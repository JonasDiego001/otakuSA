package com.otakeiros.otakusa.entidades;

public class Usuario {

    private String nome;
    private String nick;
    private String senha;
    private String email;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFraseEfeito(String fraseEfeito) {
        this.fraseEfeito = fraseEfeito;
    }

    public String getNome() {
        return nome;
    }

    public String getNick() {
        return nick;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getFraseEfeito() {
        return fraseEfeito;
    }

    private String fraseEfeito;

}
