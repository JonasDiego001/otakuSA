package com.otakeiros.otakusa.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.otakeiros.otakusa.MainActivity;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.FansubDao;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.banco.repositorios.FansubRepositorio;
import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

import static com.otakeiros.otakusa.MainActivity.USUARIO_LOGADO;

public class GerenciarFansub extends AppCompatActivity {
    public Toolbar toob;
    private FansubRepositorio mRepositorio;
    public FansubDao mDao;
    public List<Integer> ids;
    public List<String> conteiner;
    public Integer idSelecionado;
    public Integer posicaoSelecionada=0;
    public List<Boolean> habilitado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_fansub);
        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Gerenciar Fansub");

        EntitysRoomDatabase db = EntitysRoomDatabase.getDatabase(getApplication());
        mRepositorio = new FansubRepositorio(getApplication());
        mDao = db.fansubDao();
        new validarFansubAsyncTask(mDao).execute();
        Button button = findViewById(R.id.cadastrarFansub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome;
                EditText edt_nome = findViewById(R.id.et_nome_gerenciar_fansub);
                nome = edt_nome.getText().toString();
                Fansub aux = new Fansub();
                aux.setNome(nome);
                aux.setHabilitado(true);
                mRepositorio.inserirFansub(aux);
                edt_nome.setText("");
                Intent intent = new Intent(GerenciarFansub.this, GerenciarFansub.class);
                startActivity(intent);
            }
        });

        ListView listaF = (ListView) findViewById(R.id.lista_fansub);
        listaF.setAdapter((ListAdapter) conteiner);
        //selecionando uma fansub na lista
        listaF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                posicaoSelecionada = position;
                idSelecionado = ids.get(position);
                EditText edt_nome = findViewById(R.id.et_nome_gerenciar_fansub);
                edt_nome.setText(conteiner.get(position));
            }
        });
        Button button2 = findViewById(R.id.atualizarFansub);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome;
                EditText edt_nome = findViewById(R.id.et_nome_gerenciar_fansub);
                nome = edt_nome.getText().toString();
                Fansub aux = new Fansub();
                aux.setNome(nome);
                aux.setId(idSelecionado);
                mRepositorio.updateFansub(aux);
                Intent intent = new Intent(GerenciarFansub.this, GerenciarFansub.class);
                startActivity(intent);
            }
        });

        Button deletar = findViewById(R.id.apagarFansub);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome;
                EditText edt_nome = findViewById(R.id.et_nome_gerenciar_fansub);
                nome = edt_nome.getText().toString();

                mRepositorio.deleteAllFansub(idSelecionado,false);
                Intent intent = new Intent(GerenciarFansub.this, GerenciarFansub.class);
                startActivity(intent);
            }
        });
    }
    private void preencherDados(List<Fansub> dados){
        ListView listaFansub = (ListView) findViewById(R.id.lista_fansub);
        conteiner = new ArrayList<>();
        ids = new ArrayList<>();
        habilitado = new ArrayList<>();
        for(int i = 0; i<dados.size(); i++){
            if(dados.get(i).getHabilitado() == true) {
                conteiner.add(dados.get(i).getNome());
                ids.add(dados.get(i).getId());
                habilitado.add(dados.get(i).getHabilitado());
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, conteiner);
        listaFansub.setAdapter(arrayAdapter);
    }
    public void atualiza(View view) {
        String nome;
        EditText et_nome = findViewById(R.id.et_nome_gerenciar_fansub);
        if (et_nome.getText().toString().equals(""))
            et_nome.requestFocus();
        else {
            nome = et_nome.getText().toString();
            Fansub fan = new Fansub();

            fan.setNome(nome);
            mRepositorio.inserirFansub(fan);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fansub,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                intent = new Intent(this, PerfilUsuario.class);
                startActivity(intent);
                break;
            case R.id.cadastrarAnime:
                intent = new Intent(this, CadastrarAnimeActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
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
    }
}
