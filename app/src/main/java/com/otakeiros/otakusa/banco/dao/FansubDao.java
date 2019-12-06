package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.List;

@Dao
public interface FansubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_fansub(Fansub fansub);

    @Query("DELETE FROM fansub")
    void delete_all_fansub();

    @Query("SELECT * FROM fansub WHERE id=:id")
    List<Fansub>get_fansub(Integer id);

    @Query("SELECT  * FROM fansub")
    LiveData<List<Fansub>>getAllFansub();

    @Query("UPDATE fansub SET nome =:nome WHERE id=:id")
    void update_fansub(String nome, Integer id);

}
