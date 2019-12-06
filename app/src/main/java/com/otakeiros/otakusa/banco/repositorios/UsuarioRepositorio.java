package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.List;

public class UsuarioRepositorio {

    private UsuarioDao mDao;
    private LiveData<List<Usuario>>listUsers;


    public UsuarioRepositorio(Application application){
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(application);
        mDao = database.userDao();
    }

    public void inserirUsuario(Usuario user){
        mDao.insert_usuario(user);
    }

    public void deleteAllUser(){
        mDao.delete_all_users();
    }

    public List<Usuario>getUser(String email){
        List<Usuario> user;
        user = mDao.get_user(email);
        return user;
    }

    public LiveData<List<Usuario>> getAllUser(){
       return mDao.getAllUsuario();
    }

    public void updateUser(String email, String newEmail,String nome, String senha, String frase, String nick){
        mDao.update_email(newEmail,email);
        mDao.update_nome(nome,email);
        mDao.update_frase(frase,email);
        mDao.update_nick(nick, email);
        mDao.update_senha(senha, email);
    }
}
