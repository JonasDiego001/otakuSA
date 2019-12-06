package com.otakeiros.otakusa.banco.dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.otakeiros.otakusa.entidades.Anime;
import com.otakeiros.otakusa.entidades.Avaliacao;
import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Imagem;
import com.otakeiros.otakusa.entidades.Permisao;
import com.otakeiros.otakusa.entidades.Usuario;


////Aqui em entities contem uma lista de entidades, para cada entidade a ser criada no banco vc tem que adicionar aqui
@Database(entities = {Usuario.class, Fansub.class, Permisao.class, Imagem.class, Avaliacao.class, Anime.class,}, version = 1, exportSchema = false)
public abstract class EntitysRoomDatabase extends RoomDatabase {
    ///Aqui vc captura os dao databases
    public abstract UsuarioDao userDao();
    public abstract FansubDao fansubDao();
    public abstract PermisaoDao permisaoDao();
    public abstract ImagemDao imagemDao();
    public abstract AvaliacaoDao avaliacaoDao();
    public abstract AnimeDao animeDao();


    private static volatile EntitysRoomDatabase INSTANCE;

    public static EntitysRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EntitysRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EntitysRoomDatabase.class, "otakusSA")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


