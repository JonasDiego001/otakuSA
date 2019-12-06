package com.otakeiros.otakusa.banco.dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.otakeiros.otakusa.entidades.Usuario;


////Aqui em entities contem uma lista de entidades, para cada entidade a ser criada no banco vc tem que adicionar aqui
@Database(entities = {Usuario.class,}, version = 1, exportSchema = false)

public abstract class EntitysRoomDatabase extends RoomDatabase {
    ///Aqui vc captura os dao databases
    public abstract UsuarioDao userDao();

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


