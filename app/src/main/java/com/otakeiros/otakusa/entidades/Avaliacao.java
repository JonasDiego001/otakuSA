package com.otakeiros.otakusa.entidades;

public class Avaliacao {
    private Integer id;

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

    private Double nota;
}
