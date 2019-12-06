package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Permisao;

import java.util.List;

@Dao
public interface PermisaoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_permisao(Permisao permisao);

    @Query("DELETE FROM permisao")
    void delete_all_permisao();

    @Query("SELECT * FROM permisao WHERE id=:id")
    List<Permisao>get_permisao(Integer id);

    @Query("SELECT  * FROM permisao")
    LiveData<List<Permisao>>getAllPermisao();

    @Query("UPDATE permisao SET permi =:permi WHERE id=:id")
    void update_permisao(Boolean permi, Integer id);

}
