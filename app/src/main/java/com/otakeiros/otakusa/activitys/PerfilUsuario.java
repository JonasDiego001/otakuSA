package com.otakeiros.otakusa.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.otakeiros.otakusa.MainActivity;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.banco.repositorios.UsuarioRepositorio;
import com.otakeiros.otakusa.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

import static com.otakeiros.otakusa.activitys.EditarUsuario.email_usuario_logado;
import static com.otakeiros.otakusa.activitys.EditarUsuario.fraseEfeito_usuario_logado;
import static com.otakeiros.otakusa.activitys.EditarUsuario.habilitado_usuario_logado;
import static com.otakeiros.otakusa.activitys.EditarUsuario.nick_usuario_logado;
import static com.otakeiros.otakusa.activitys.EditarUsuario.nome_usuario_logado;
import static com.otakeiros.otakusa.activitys.EditarUsuario.senha_usuario_logado;


public class PerfilUsuario extends AppCompatActivity {
    private Integer a;
    public Toolbar toob;
    private UsuarioDao mDao;
    private String login;
    private UsuarioRepositorio mRepositorio;

    public String nome = null;
    private String nick;
    private String senha;
    private String email;
    private String fraseEfeito;
    private Boolean habilitado;
    public static String USUARIO_LOGADO_PERFIL = null;


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

        Button deletar = findViewById(R.id.apagarUsuario);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepositorio.deleteAllUser(false, email);
                Intent intent = new Intent(PerfilUsuario.this, Login.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void editar_usuario(View view) {
        nome_usuario_logado = nome;
        nick_usuario_logado = nick;
        email_usuario_logado = email;
        senha_usuario_logado = senha;
        fraseEfeito_usuario_logado = fraseEfeito;
        habilitado_usuario_logado = habilitado;
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencherDados(List<Usuario> dados){
        for(int i = 0; i != 1; i++){
            if(dados.get(i).getHabilitado() == true) {
                nome = dados.get(i).getNome();
                nick = dados.get(i).getNick();
                email = dados.get(i).getEmail();
                habilitado = dados.get(i).getHabilitado();
                fraseEfeito = dados.get(i).getFraseEfeito();
                senha = dados.get(i).getSenha();
            }
            TextView tv1 = findViewById(R.id.tv_nome_usuario);
            TextView tv2 = findViewById(R.id.tv_email_usuario);
            TextView tv3 = findViewById(R.id.tv_nick_usuario);
            TextView tv4 = findViewById(R.id.tv_fraseEfeito_usuario);
            TextView tv5 = findViewById(R.id.tv_habilitado);
            tv1.setText(nome);
            tv2.setText(email);
            tv3.setText(nick);
            tv4.setText(fraseEfeito);
            tv5.setText(habilitado.toString());
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
            usuarios = dao.get_user(USUARIO_LOGADO_PERFIL,true);
            return usuarios;
        }

        @Override
        protected void onPostExecute(List usuarioG) {
            super.onPostExecute(usuarioG);
            if (usuarioG.size() > 0) preencherDados(usuarioG);

        }
    }
}
