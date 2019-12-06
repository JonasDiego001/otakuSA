package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Imagem {

    @ColumnInfo(name = "caminoImagem")
    private String caminoImagem;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    public void setCaminoImagem(String caminoImagem) {
        this.caminoImagem = caminoImagem;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaminoImagem() {
        return caminoImagem;
    }

    public Integer getId() {
        return id;
    }
}
