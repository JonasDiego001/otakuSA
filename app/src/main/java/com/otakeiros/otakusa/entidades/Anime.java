package com.otakeiros.otakusa.entidades;

public class Anime {
    private String nome;
    private Integer AnoLancamento;
    private Integer numEpisodio;
    private Boolean censura;
    private Integer notaMedia;
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

    public void setCensura(Boolean censura) {
        this.censura = censura;
    }

    public void setNotaMedia(Integer notaMedia) {
        this.notaMedia = notaMedia;
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

    public Integer getNumEpisodio() {
        return numEpisodio;
    }

    public Boolean getCensura() {
        return censura;
    }

    public Integer getNotaMedia() {
        return notaMedia;
    }

    public String getSinops() {
        return sinops;
    }
}
