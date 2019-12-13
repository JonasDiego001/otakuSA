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
    public static String nome_usuario_logado;
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

        EditText ed1 = findViewById(R.id.et_nome_usuario_editar);
        EditText ed3 = findViewById(R.id.et_nick_editar);
        EditText ed4 = findViewById(R.id.et_frase_efeito_editar);
        ed1.setText(nome_usuario_logado);
        ed3.setText(nick_usuario_logado);
        ed4.setText(fraseEfeito_usuario_logado);

        Log.i("nome",nome_usuario_logado);
        Log.i("nick",nick_usuario_logado);
        Log.i("email",email_usuario_logado);
        Log.i("frase",fraseEfeito_usuario_logado);
        Log.i("senha",senha_usuario_logado);
        Log.i("habilitado", ""+habilitado_usuario_logado);
    }

    public void confirmar_edicao(View view) {
        String nome;
        String senha;
        String nick;
        String frase;
        String confirmacao_senha;

        EditText et_nome = findViewById(R.id.et_nome_usuario_editar);
        EditText et_nick = findViewById(R.id.et_nick_editar);
        EditText et_senha = findViewById(R.id.et_senha_usuario_edita);
        EditText et_conirmacao_senha = findViewById(R.id.et_confirmacao_senha_editar);
        EditText et_frase = findViewById(R.id.et_frase_efeito_editar);

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

            Log.i("novo nome",nome);
            Log.i("novo nick",nick);
            Log.i("novo email",email_usuario_logado);
            Log.i("novo frase",frase);
            Log.i("novo senha",confirmacao_senha);

            if (senha.equals(confirmacao_senha)) {
                mRepositorio.updateUser(email_usuario_logado, nome, confirmacao_senha, frase, nick, habilitado_usuario_logado);
                startActivity(new Intent(getApplicationContext(), PerfilUsuario.class));

            } else {
                et_conirmacao_senha.requestFocus();
                //senha não condiz com a senha de confirmação;
            }
        }
    }
}
