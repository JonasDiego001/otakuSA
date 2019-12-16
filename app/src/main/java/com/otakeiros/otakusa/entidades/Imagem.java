package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "imagem")
public class Imagem {

    @ColumnInfo(name = "caminoImagem")
    private String caminoImagem;

    @PrimaryKey(autoGenerate = true)
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
