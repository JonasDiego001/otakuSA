package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Fansub {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "nome")
    private String nome;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
