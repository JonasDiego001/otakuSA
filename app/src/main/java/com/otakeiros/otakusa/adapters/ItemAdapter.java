package com.otakeiros.otakusa.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.otakeiros.otakusa.Item;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.ImagemDao;
import com.otakeiros.otakusa.entidades.Anime;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> itemList;
    public MyViewHolder mHolder;

    public ItemAdapter(Context c, List<Item> itemList) {
        this.mContext = c;
        this.itemList = itemList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anime_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Item item = itemList.get(position);
        final Anime anime = item.getItemAnime();

        holder.tv_nota.setText(String.valueOf(anime.getNotaMedia()));
        holder.tv_nome.setText(anime.getNome());
        mHolder = holder;
        ImagemDao dao = EntitysRoomDatabase.getDatabase(mContext).imagemDao();
        new getImagemAsync(dao).execute(anime.getId());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nome;
        private TextView tv_nota;
        private ImageView iv_imagem;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_nome = itemView.findViewById(R.id.tv_nome_item);
            tv_nota = itemView.findViewById(R.id.tv_nota_item);
            iv_imagem = itemView.findViewById(R.id.iv_image_anime_item);
        }
    }

    public class getImagemAsync extends AsyncTask<Integer, Void, String> {
        ImagemDao dao;

        getImagemAsync(ImagemDao dao) {
            this.dao = dao;
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return dao.getImagem(integers[0]).get(0).getCaminoImagem();
        }

        @Override
        protected void onPostExecute(String local) {
            super.onPostExecute(local);

            File file = new File(local);
            if (file.exists() && file.canRead()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                    Bitmap imagem = BitmapFactory.decodeStream(bufferedInputStream);
                    Bitmap resized = Bitmap.createScaledBitmap(imagem, 150, 150, false);
                    mHolder.iv_imagem.setImageBitmap(resized);
                    Log.i("NELORE","IMAGEM SET : "+local);

                } catch (FileNotFoundException e) {
                    Log.i("NELORE","ALGUMA IMAGEM NAO ESTA NO BD");
                    e.printStackTrace();
                }
            }
        }
    }
}