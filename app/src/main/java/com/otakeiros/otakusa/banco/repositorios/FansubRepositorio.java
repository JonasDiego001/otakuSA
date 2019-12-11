package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.otakeiros.otakusa.banco.dao.AnimeDao;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.FansubDao;
import com.otakeiros.otakusa.entidades.Anime;
import com.otakeiros.otakusa.entidades.Fansub;

import java.util.List;

public class FansubRepositorio {

    private FansubDao mDao;
    private LiveData<List<Fansub>> listFansub;


    public FansubRepositorio(Application application) {
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(application);
        mDao = database.fansubDao();
    }

    /*public void inserirFansub(Fansub fan) {
        new insertAsyncTask(mDao).execute(fan);
    }*/

    public void deleteAllFansub() {
        mDao.delete_all_fansub();
    }

    public List<Fansub> getFansub(Integer id) {
        List<Fansub> fans;
        fans = mDao.get_fansub(id);
        return fans;
    }

    public LiveData<List<Fansub>> getAllFansub() {
        return mDao.getAllFansub();
    }

    public void updateFansub(String nome) {
        new updateAsyncTask(mDao).execute(nome);
    }


   /* private class insertAsyncTask extends AsyncTask<Anime, Void, Void> {
        public insertAsyncTask(FansubDao mDao) {
        }

        @Override
        protected Void doInBackground(Fansub... fansubs) {
            mDao.insert_fansub(fansubs[0]);
            return null;
        }
    }*/

    private class updateAsyncTask extends AsyncTask<String, Void, Void> {

        FansubDao mDao;

        public updateAsyncTask(FansubDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(String... params) {
            mDao.update_fansub(params[2], Integer.parseInt(params[0]));
            return null;
        }
    }

}
