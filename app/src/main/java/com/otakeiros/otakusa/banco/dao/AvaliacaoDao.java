package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.otakeiros.otakusa.entidades.Avaliacao;
import com.otakeiros.otakusa.entidades.Fansub;

import java.util.List;

@Dao
public interface AvaliacaoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_fansub(Avaliacao avaliacao);

    @Query("DELETE FROM avaliacao")
    void delete_all_avaliacao();

    @Query("SELECT * FROM avaliacao WHERE id=:id")
    List<Avaliacao>get_avaliacao(Integer id);

    @Query("SELECT  * FROM avaliacao")
    LiveData<List<Avaliacao>>getAllAvaliacao();

    @Query("UPDATE avaliacao SET nota =:nota WHERE id=:id")
    void update_fansub(Double nota, Integer id);

}
