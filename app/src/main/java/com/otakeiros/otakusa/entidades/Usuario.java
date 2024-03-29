package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class Usuario {

    @ColumnInfo (name = "nome")
    private String nome;
    @ColumnInfo (name = "nick")
    private String nick;
    @ColumnInfo (name = "senha")
    private String senha;
    @PrimaryKey @NonNull
    @ColumnInfo (name = "email")
    private String email;
    @ColumnInfo (name = "habilitado")
    private Boolean habilitado;

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }


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
