package com.otakeiros.otakusa.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.repositorios.UsuarioRepositorio;

public class EditarUsuario extends AppCompatActivity {
    public Toolbar toob;
    private String login;
    public static String nome_usuario_logado = null;
    public static String nick_usuario_logado;
    public static String email_usuario_logado;
    public static String fraseEfeito_usuario_logado;
    public static String senha_usuario_logado;
    public static Boolean habilitado_usuario_logado;
    private UsuarioRepositorio mRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        mRepositorio = new UsuarioRepositorio(getApplication());

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Editar Usuario");

        EditText ed1 = findViewById(R.id.et_nome_usuario);
        EditText ed3 = findViewById(R.id.et_nick);
        EditText ed4 = findViewById(R.id.et_frase_efeito_usuario_cadastro);
        ed1.setText(nome_usuario_logado);
        ed3.setText(nick_usuario_logado);
        ed4.setText(fraseEfeito_usuario_logado);
        Log.i("vai dar merda","VRAU");
    }

    public void confirmar_edicao(View view) {
        String nome;
        String senha;
        String nick;
        String frase;
        String confirmacao_senha;

        EditText et_nome = findViewById(R.id.et_nome_usuario);
        EditText et_nick = findViewById(R.id.et_nick);
        EditText et_senha = findViewById(R.id.et_senha_usuario_cadastro_usuario);
        EditText et_conirmacao_senha = findViewById(R.id.et_confirmacao_senha_cadastro_usuario);
        EditText et_frase = findViewById(R.id.et_frase_efeito_usuario_cadastro);




        if (et_nome.getText().toString().equals(""))
            et_nome.requestFocus();
        else if (et_senha.getText().toString().equals(""))
            et_senha.requestFocus();
        else if (et_nick.getText().toString().equals(""))
            et_nick.requestFocus();
        else if (et_frase.getText().toString().equals(""))
            et_frase.requestFocus();
        else if (et_conirmacao_senha.getText().toString().equals(""))
            et_conirmacao_senha.requestFocus();
        else {
            nome = et_nome.getText().toString();
            senha = et_senha.getText().toString();
            nick = et_nick.getText().toString();
            confirmacao_senha = et_conirmacao_senha.getText().toString();
            frase = et_frase.getText().toString();

            Log.i("vai dar merda", nome);
            Log.i("vai dar merda", nick);
            Log.i("vai dar merda", frase);

            if (senha.equals(confirmacao_senha)) {
                mRepositorio.updateUser(email_usuario_logado, nome, senha, frase, nick, habilitado_usuario_logado);
                startActivity(new Intent(getApplicationContext(), PerfilUsuario.class));

            } else {
                et_conirmacao_senha.requestFocus();
                //senha não condiz com a senha de confirmação;
            }

        }
    }
}
