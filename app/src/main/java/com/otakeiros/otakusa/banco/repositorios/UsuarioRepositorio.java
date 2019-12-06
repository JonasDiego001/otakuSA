package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.entidades.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    private UsuarioDao mDao;
    private LiveData<List<Usuario>> listUsers;


    public UsuarioRepositorio(Application application) {
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(application);
        mDao = database.userDao();
    }

    public void inserirUsuario(Usuario user) {
        new insertAsyncTask(mDao).execute(user);
    }

    public void deleteAllUser() {
        mDao.delete_all_users();
    }

    public List<Usuario> getUser(String email) {
        List<Usuario> user;
        user = mDao.get_user(email);
        return user;
    }

    public LiveData<List<Usuario>> getAllUser() {
        return mDao.getAllUsuario();
    }

    public void updateUser(String email, String newEmail, String nome, String senha, String frase, String nick) {
        new updateAsyncTask(mDao).execute(email, newEmail, nome, senha, frase, nick);
    }


    private class insertAsyncTask extends AsyncTask<Usuario, Void, Void> {
        public insertAsyncTask(UsuarioDao mDao) {
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            mDao.insert_usuario(usuarios[0]);
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<String, Void, Void> {

        UsuarioDao mDao;

        public updateAsyncTask(UsuarioDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(String... params) {
            mDao.update_nome(params[2], params[0]);
            mDao.update_frase(params[3], params[0]);
            mDao.update_nick(params[4], params[0]);
            mDao.update_senha(params[5], params[0]);
            ///COMO O EMAIL É CHAVE PRIMARIA, DEVE SER ATUALIZADO POR ULTIMO
            mDao.update_email(params[1], params[0]);
            return null;
        }
    }

}
