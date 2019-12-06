package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "permisao")
public class Permisao {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "permisao")
    private Boolean permisao;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPermisao(Boolean permisao) {
        this.permisao = permisao;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getPermisao() {
        return permisao;
    }
}
