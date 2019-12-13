package com.otakeiros.otakusa.banco.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.otakeiros.otakusa.entidades.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_usuario(Usuario user);

    @Query("UPDATE usuario SET habilitado=:habilitado WHERE email=:email")
    void delete_all_user(Boolean habilitado,String email);

    @Query("SELECT * FROM usuario WHERE email=:email AND habilitado=:habilitado")
    List<Usuario>get_user(String email, Boolean habilitado);

    @Query("SELECT  * FROM usuario")
    LiveData<List<Usuario>>getAllUsuario();

    @Query("UPDATE usuario SET nome =:nome WHERE email=:email")
    void update_nome(String nome,String email);

    @Query("UPDATE usuario SET senha=:senha WHERE email=:email")
    void update_senha(String senha,String email);

    @Query("UPDATE usuario SET fraseEfeito =:frase WHERE email=:email")
    void update_frase(String frase,String email);

    @Query("UPDATE usuario SET nick =:nick WHERE email=:email")
    void update_nick(String nick,String email);

    @Query("UPDATE usuario SET habilitado =:habilitado WHERE email=:email")
    void update_habilitado(Boolean habilitado,String email);
}
