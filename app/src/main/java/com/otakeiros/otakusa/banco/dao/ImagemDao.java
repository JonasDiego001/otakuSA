package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Imagem;

import java.util.List;

@Dao
public interface ImagemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_imagem(Imagem imagem);

    @Query("DELETE FROM imagem")
    void delete_all_imagem();

    @Query("SELECT * FROM imagem WHERE id=:id")
    List<Imagem>getImagem(Integer id);

    @Query("SELECT  * FROM imagem")
    LiveData<List<Imagem>>getAllImagem();

    @Query("UPDATE imagem SET caminoImagem =:caminoImagem WHERE id=:id")
    void updateImagem(String caminoImagem, Integer id);

}
