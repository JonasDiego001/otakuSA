package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.otakeiros.otakusa.entidades.Anime;
import com.otakeiros.otakusa.entidades.Fansub;

import java.util.List;

@Dao
public interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_anime(Anime anime);

    @Query("DELETE FROM anime")
    void delete_all_anime();

    @Query("SELECT * FROM anime WHERE id=:id")
    List<Anime>get_anime(Integer id);

    @Query("SELECT  * FROM anime")
    LiveData<List<Anime>>getAllAnime();

    @Query("UPDATE anime SET nome =:nome WHERE id=:id")
    void update_nome(String nome,Integer id);

    @Query("UPDATE anime SET sinops=:sinops WHERE id=:id")
    void update_sinops(String sinops, Integer id);

    @Query("UPDATE anime SET anoLancamento =:anoLancamento WHERE id=:id")
    void update_anoLancamento(Integer anoLancamento,Integer id);

    @Query("UPDATE anime SET numEpisodio=:numEpisodio WHERE id=:id")
    void update_senha(Integer numEpisodio,Integer id);

    @Query("UPDATE anime SET notaMedia=:notaMedia WHERE id=:id")
    void update_notaMedia(Double notaMedia,Integer id);

    @Query("UPDATE anime SET numEpisodio=:numEpisodio WHERE id=:id")
    void update_numEpisodio(Integer numEpisodio,Integer id);

}
