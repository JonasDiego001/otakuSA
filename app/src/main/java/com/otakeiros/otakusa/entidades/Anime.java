package com.otakeiros.otakusa.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "anime")
public class Anime {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "anoLancamento")
    private Integer AnoLancamento;
    @ColumnInfo(name = "numEpisodio")
    private Integer numEpisodio;
    @ColumnInfo(name = "notaMedia")
    private Double notaMedia;
    @ColumnInfo(name = "sinops")
    private String sinops;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        AnoLancamento = anoLancamento;
    }

    public void setNumEpisodio(Integer numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public void setSinops(String sinops) {
        this.sinops = sinops;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoLancamento() {
        return AnoLancamento;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public Integer getNumEpisodio() {
        return numEpisodio;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public String getSinops() {
        return sinops;
    }

}
