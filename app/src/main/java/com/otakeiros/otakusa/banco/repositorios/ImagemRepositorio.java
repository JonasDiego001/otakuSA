package com.otakeiros.otakusa.banco.repositorios;

import android.app.Application;
import android.media.Image;
import android.os.AsyncTask;

import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.ImagemDao;
import com.otakeiros.otakusa.entidades.Imagem;

public class ImagemRepositorio {
    ImagemDao imagemDao;

    public ImagemRepositorio(Application application) {
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(application);
        imagemDao = database.imagemDao();
    }

    public void inserirImagem(Imagem imagem) {
        new insertAsyncTask(imagemDao).execute(imagem);
    }

    private static class insertAsyncTask extends AsyncTask<Imagem, Void, Void> {
        ImagemDao imagemDao;

        insertAsyncTask(ImagemDao imagemDao) {
            this.imagemDao = imagemDao;
        }

        @Override
        protected Void doInBackground(Imagem... imagems) {
            imagemDao.insert_imagem(imagems[0]);
            return null;
        }
    }
}

