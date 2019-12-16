package com.otakeiros.otakusa;

import com.otakeiros.otakusa.entidades.Anime;

public class Item {
    private Anime anime;

    public Item(Anime anime) {
        this.anime = anime;
    }

    public Anime getItemAnime() {
        return anime;
    }
}
