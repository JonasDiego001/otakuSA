package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.FansubDao;
import com.otakeiros.otakusa.entidades.Fansub;

import java.util.ArrayList;
import java.util.List;

public class FansubRepositorio {

    private FansubDao mDao;
    private LiveData<List<Fansub>> listFansub;


    public FansubRepositorio(Application application) {
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(application);
        mDao = database.fansubDao();
    }

    public void inserirFansub(Fansub fan) {
        new insertAsyncTask(mDao).execute(fan);
    }

    public void deleteAllFansub(Integer id, Boolean habilitado) {
        mDao.delete_fansub(id,habilitado);
    }

    public List<Fansub> getFansub(Integer id) {
        List<Fansub> fans;
        fans = mDao.get_fansub(id);
        return fans;
    }

    public List<Fansub> getAllFansub() {
        return mDao.getAllFansub();
    }

    public void updateFansub(Fansub fansub) {
        new updateAsyncTask(mDao).execute(fansub);
    }


   public static class insertAsyncTask extends AsyncTask<Fansub, Void, Void> {
       FansubDao dao;

        public insertAsyncTask(FansubDao mDao) {
            dao = mDao;
   }

        @Override
        protected Void doInBackground(Fansub... fansubs) {
            dao.insert_fansub(fansubs[0]);
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<Fansub, Void, Void> {

        FansubDao mDao;

        public updateAsyncTask(FansubDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Fansub... fansubs) {
            mDao.update_fansub(fansubs[0].getNome(),fansubs[0].getId());
            return null;
        }
    }

}
