package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fansub")
public class Fansub {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "habilitado")
    private Boolean habilitado;

    public Boolean getHabilitado() {
        return habilitado;
    }
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
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
