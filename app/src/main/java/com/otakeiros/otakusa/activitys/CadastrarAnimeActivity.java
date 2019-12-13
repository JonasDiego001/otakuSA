package com.otakeiros.otakusa.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.otakeiros.otakusa.MainActivity;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.FansubDao;
import com.otakeiros.otakusa.banco.repositorios.AnimeRepositorio;
import com.otakeiros.otakusa.banco.repositorios.FansubRepositorio;
import com.otakeiros.otakusa.entidades.Anime;
import androidx.appcompat.widget.Toolbar;

import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CadastrarAnimeActivity extends AppCompatActivity {
    public Toolbar toob;
    private AnimeRepositorio mRepositorio;
    private FansubRepositorio mRepositorio2;
    private FansubDao mDao;
    public List<Integer> ids;
    public List<String> conteiner;
    public Integer idSelecionado;
    public Integer posicaoSelecionada=0;
    public List<Boolean> habilitado;
    public static volatile int CONTROLE = 0 ;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anime);
        mRepositorio = new AnimeRepositorio(getApplication());

        EntitysRoomDatabase db = EntitysRoomDatabase.getDatabase(getApplication());
        mRepositorio2 = new FansubRepositorio(getApplication());
        mDao = db.fansubDao();
        new validarFansubAsyncTask(mDao).execute();

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Cadrastro Anime");

        TextView tv_sexo = findViewById(R.id.tv_Selecionar_Fansub);
        tv_sexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSexoAlertDialog(v);
            }
        });
    }

    public void confirmar_cadastro(View view) {
        String nome;
        Integer ano;
        Integer numEpisodio;
        double notaInicial;
        String sinopse;

        EditText et_nome = findViewById(R.id.et_nome_usuario_cadastro_anime);
        EditText et_ano = findViewById(R.id.et_ano_cadastro_anime);
        EditText et_numEpisodio = findViewById(R.id.et_numEpisodio_cadastro_anime);
        EditText et_notaInicial = findViewById(R.id.et_nota_cadastro_anime);
        EditText et_sinopse = findViewById(R.id.et_sinopse_usuario_cadastro);

        if (et_nome.getText().toString().equals(""))
            et_nome.requestFocus();
        else if (et_ano.getText().toString().equals(""))
            et_ano.requestFocus();
        else if (et_numEpisodio.getText().toString().equals(""))
            et_numEpisodio.requestFocus();
        else if (et_notaInicial.getText().toString().equals(""))
            et_notaInicial.requestFocus();
        else if (et_sinopse.getText().toString().equals(""))
            et_sinopse.requestFocus();
        else{
            nome = et_nome.getText().toString();
            ano = Integer.parseInt(et_ano.getText().toString());
            numEpisodio = Integer.parseInt(et_ano.getText().toString());
            notaInicial = Double.parseDouble(et_notaInicial.getText().toString());
            sinopse = et_sinopse.getText().toString();

            Anime anim = new Anime();
            anim.setNome(nome);
            anim.setNumEpisodio(numEpisodio);
            anim.setNumEpisodio(numEpisodio);
            anim.setAnoLancamento(ano);
            anim.setNotaMedia(notaInicial);
            anim.setSinops(sinopse);

            mRepositorio.inserirAnime(anim);

            et_nome.setText("");
            et_ano.setText("");
            et_numEpisodio.setText("");
            et_notaInicial.setText("");
            et_sinopse.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastraranime,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.gerenciarFansub:
                intent = new Intent(this, GerenciarFansub.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                intent = new Intent(this, PerfilUsuario.class);
                startActivity(intent);
                break;
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getSexoAlertDialog(final View v) {

        final TextView tv = findViewById(R.id.tv_Selecionar_Fansub);
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarAnimeActivity.this);
        builder.setTitle("Selecione seu sexo");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                CadastrarAnimeActivity.this,
                android.R.layout.select_dialog_singlechoice);


        for (int i = 0; i<conteiner.size(); i++) {
            arrayAdapter.add(conteiner.get(i));
        }
        builder.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("nelore", arrayAdapter.getItem(which));
                        posicaoSelecionada = which;
                        dialog.dismiss();
                        Log.i("nelore", "which ::: -> "+posicaoSelecionada);
                        tv.setText(conteiner.get(posicaoSelecionada));

                    }
                });
        builder.show();

    }


    public class validarFansubAsyncTask extends AsyncTask<Void, Void, List> {
        public FansubDao dao;
        public validarFansubAsyncTask(FansubDao fansubDao) {
            dao = fansubDao;
        }
        @Override
        protected List<Fansub> doInBackground(Void... string) {
            List<Fansub> fansubs = new ArrayList<Fansub>();
            fansubs = dao.getAllFansub();
            return fansubs;
        }
        @Override
        protected void onPostExecute(List todasFansubs) {
            super.onPostExecute(todasFansubs);
            if (todasFansubs.size()>0) preencherDados(todasFansubs);

        }

        private void preencherDados(List<Fansub> dados){

            Log.i("NELORE", "size --> "+dados.size());
            conteiner = new ArrayList<>();
            ids = new ArrayList<>();
            habilitado = new ArrayList<>();
            for(int i = 0; i<dados.size(); i++){
                if(dados.get(i).getHabilitado() == true) {
                    conteiner.add(dados.get(i).getNome());
                    ids.add(dados.get(i).getId());
                    habilitado.add(dados.get(i).getHabilitado());
                    Log.i("nelore", "LOOOOPPP");
                }

            }
        }
    }
}
