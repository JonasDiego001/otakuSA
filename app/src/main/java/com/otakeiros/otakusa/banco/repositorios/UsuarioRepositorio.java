package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.entidades.Usuario;

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

    public void deleteAllUser(Boolean habilitado, String email) {
        Usuario aux = new Usuario();
        aux.setHabilitado(habilitado);
        aux.setEmail(email);
        Log.i("NELORE", email);
        new daleAsyncTask(mDao).execute(aux);
    }

    public List<Usuario> getUser(String email, Boolean habilitado) {
        List<Usuario> user;
        user = mDao.get_user(email, habilitado);
        return user;
    }

    public LiveData<List<Usuario>> getAllUser() {
        return mDao.getAllUsuario();
    }

    public void updateUser(String email, String nome, String senha, String frase, String nick, Boolean habilitado) {
        new updateAsyncTask(mDao).execute(email, nome, senha, frase, nick, habilitado.toString());
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
    private class daleAsyncTask extends AsyncTask<Usuario, Void, Void> {
        UsuarioDao mDao;
        public daleAsyncTask(UsuarioDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            Log.i("vai dar", ""+usuarios[0].getHabilitado());
            Log.i("que vai", usuarios[0].getEmail());
            mDao.delete_all_user(usuarios[0].getHabilitado(),usuarios[0].getEmail());
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
            mDao.update_nome(params[1], params[0]);
            mDao.update_frase(params[2], params[0]);
            mDao.update_nick(params[3], params[0]);
            mDao.update_senha(params[4], params[0]);
            mDao.update_habilitado(Boolean.parseBoolean(params[5]),params[0]);
            return null;
        }
    }

}
