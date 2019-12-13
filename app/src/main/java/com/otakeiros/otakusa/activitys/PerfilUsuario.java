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
import android.widget.TextView;

import com.otakeiros.otakusa.MainActivity;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.FansubDao;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.banco.repositorios.FansubRepositorio;
import com.otakeiros.otakusa.banco.repositorios.UsuarioRepositorio;
import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PerfilUsuario extends AppCompatActivity {
    private Integer a;
    public Toolbar toob;
    private UsuarioDao mDao;
    private String login;
    private UsuarioRepositorio mRepositorio;

    public String nome;
    private String nick;
    private String senha;
    private String email;
    private String fraseEfeito;
    private Boolean habilitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Perfil Usuario");

        EntitysRoomDatabase db = EntitysRoomDatabase.getDatabase(getApplication());
        mRepositorio = new UsuarioRepositorio(getApplication());
        mDao = db.userDao();
        new validarUsuarioAsyncTask(mDao).execute();

        TextView tv1 = findViewById(R.id.tv_nome_usuario);
        TextView tv2 = findViewById(R.id.tv_email_usuario);
        TextView tv3 = findViewById(R.id.tv_nick_usuario);
        TextView tv4 = findViewById(R.id.tv_fraseEfeito_usuario);
        tv1.setText(nome);
        tv2.setText(email);
        tv3.setText(nick);
        tv4.setText(fraseEfeito);
    }

    public void editar_usuario(View view) {
        startActivity(new Intent(this,EditarUsuario.class));
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
            case R.id.gerenciarFansub:
                intent = new Intent(this, GerenciarFansub.class);
                startActivity(intent);
                break;
            case R.id.cadastrarAnime:
                intent = new Intent(this, CadastrarAnimeActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencherDados(List<Usuario> dados){
        for(int i = 0; i<dados.size(); i++){
            if(dados.get(i).getHabilitado() == true) {
                nome = dados.get(i).getNome();
                nick = dados.get(i).getNick();
                senha = dados.get(i).getSenha();
                email = dados.get(i).getEmail();
                habilitado = dados.get(i).getHabilitado();
                fraseEfeito = dados.get(i).getFraseEfeito();
            }

        }
    }
    public class validarUsuarioAsyncTask extends AsyncTask<Void, Void, List> {
        public UsuarioDao dao;

        public validarUsuarioAsyncTask(UsuarioDao usuarioDao) {
            dao = usuarioDao;
        }

        @Override
        protected List<Usuario> doInBackground(Void... string) {
            List<Usuario> usuarios = new ArrayList<Usuario>();
            usuarios = dao.get_user(login);
            return usuarios;
        }

        @Override
        protected void onPostExecute(List usuarioG) {
            super.onPostExecute(usuarioG);
            if (usuarioG.size() > 0) preencherDados(usuarioG);

        }
    }
}
