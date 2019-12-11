package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.otakeiros.otakusa.banco.dao.AnimeDao;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.entidades.Anime;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.List;

public class AnimeRepositorio {

    private AnimeDao mDao;
    private LiveData<List<Anime>> listAnime;


    public AnimeRepositorio(Application application) {
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(application);
        mDao = database.animeDao();
    }

    public void inserirAnime(Anime ani) {
        new insertAsyncTask(mDao).execute(ani);
    }

    public void deleteAllAnime() {
        mDao.delete_all_anime();
    }

    public List<Anime> getAnime(Integer id) {
        List<Anime> ani;
        ani = mDao.get_anime(id);
        return ani;
    }

    public LiveData<List<Anime>> getAllAnime() {
        return mDao.getAllAnime();
    }

    public void updateAnime(String nome, Integer AnoLancamento, Integer numEpisodio, Double notaMedia, String sinops) {
        new updateAsyncTask(mDao).execute(nome, Integer.toString(AnoLancamento), Integer.toString(numEpisodio), Double.toString(notaMedia), sinops);
    }


    private class insertAsyncTask extends AsyncTask<Anime, Void, Void> {
        public insertAsyncTask(AnimeDao mDao) {
        }

        @Override
        protected Void doInBackground(Anime... animes) {
            mDao.insert_anime(animes[0]);
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<String, Void, Void> {

        AnimeDao mDao;

        public updateAsyncTask(AnimeDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(String... params) {
            mDao.update_nome(params[2], Integer.parseInt(params[0]));
            mDao.update_anoLancamento(Integer.parseInt(params[3]), Integer.parseInt(params[0]));
            mDao.update_numEpisodio(Integer.parseInt(params[4]), Integer.parseInt(params[0]));
            mDao.update_notaMedia(Double.parseDouble(params[5]), Integer.parseInt(params[0]));
            mDao.update_sinops(params[6], Integer.parseInt(params[0]));
            return null;
        }
    }

}
