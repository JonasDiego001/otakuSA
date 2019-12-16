package com.otakeiros.otakusa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.otakeiros.otakusa.activitys.CadastrarAnimeActivity;
import com.otakeiros.otakusa.activitys.GerenciarFansub;
import com.otakeiros.otakusa.activitys.Login;
import com.otakeiros.otakusa.activitys.PerfilUsuario;
import com.otakeiros.otakusa.adapters.ItemAdapter;
import com.otakeiros.otakusa.banco.dao.AnimeDao;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.entidades.Anime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.otakeiros.otakusa.activitys.PerfilUsuario.USUARIO_LOGADO_PERFIL;

public class MainActivity extends AppCompatActivity {

    public Toolbar toob;
    public static String USUARIO_LOGADO = null;
    private String login;
    private List<Anime> animesList;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Animes");


        if (USUARIO_LOGADO == null)
            startActivity(new Intent(getApplicationContext(), Login.class));

        Spinner spinnerLetra = findViewById(R.id.sp_alfabeto);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alafbeto, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLetra.setAdapter(adapter);

        Spinner spinnerAno = findViewById(R.id.sp_ano);
        ArrayAdapter<CharSequence> adapterAno = ArrayAdapter.createFromResource(this, R.array.ano, android.R.layout.simple_spinner_dropdown_item);
        adapterAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAno.setAdapter(adapterAno);


        if (!(USUARIO_LOGADO == null)) {
            EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(getApplication());
            AnimeDao dao = database.animeDao();
            animesList = new ArrayList<>();
            itemList = new ArrayList<>();
            dao.getAllAnime().observe(this, new Observer<List<Anime>>() {
                @Override
                public void onChanged(List<Anime> anime) {
                    if (anime != null)
                        for (int i = 0; i < anime.size(); i++) {
                            if (!(animesList.contains((anime.get(i))))) {
                                Log.i("NELORE", "ANIME IN RV:: : " + anime.get(i).getNome());
                                animesList.add(anime.get(i));
                                itemList.add(new Item(anime.get(i)));
                            }
                        }
                }
            });
            RecyclerView recyclerView = findViewById(R.id.rv_item);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), itemList);
            recyclerView.setAdapter(itemAdapter);
            recyclerView.setLayoutManager(layoutManager);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.gerenciarFansub:
                intent = new Intent(this, GerenciarFansub.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                USUARIO_LOGADO_PERFIL = USUARIO_LOGADO;
                intent = new Intent(this, PerfilUsuario.class);
                startActivity(intent);
                break;
            case R.id.cadastrarAnime:
                intent = new Intent(this, CadastrarAnimeActivity.class);
                startActivity(intent);
                break;
            case R.id.deslogar:
                finish();
                intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    private class getAnimesAsync extends AsyncTask<Void,Void,List<Anime>> {
        AnimeDao dao;
        public getAnimesAsync(AnimeDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<Anime> doInBackground(Void... voids) {
            return dao.getAllAnime().getValue();
        }

        @Override
        protected void onPostExecute(List<Anime> anime) {
            super.onPostExecute(anime);
            animesList = anime;
            Log.i("NELORE","RETORNOU");
            getAnimesList();
        }
    }*/
}
