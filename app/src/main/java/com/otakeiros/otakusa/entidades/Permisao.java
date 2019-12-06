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

    @ColumnInfo(name = "permi")
    private Boolean permi;

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setPermi(Boolean permi) {
        this.permi = permi;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public Boolean getPermi() {
        return permi;
    }
}
