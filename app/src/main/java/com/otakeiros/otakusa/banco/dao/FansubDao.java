package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FansubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_fansub(Fansub fansub);

    @Query("UPDATE fansub SET habilitado =:habilitado WHERE id=:id AND habilitado =:habilitado")
    void delete_fansub(Integer id, Boolean habilitado);

    @Query("SELECT * FROM fansub WHERE id=:id")
    List<Fansub>get_fansub(Integer id);

    @Query("SELECT * FROM fansub")
    List<Fansub> getAllFansub();

    @Query("UPDATE fansub SET nome =:nome WHERE id=:id")
    void update_fansub(String nome, Integer id);

}
