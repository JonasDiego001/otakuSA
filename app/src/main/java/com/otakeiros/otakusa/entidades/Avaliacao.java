package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "avaliacao")
public class Avaliacao {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "nota")
    private Double nota;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }
}
