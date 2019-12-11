package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.repositorios.UsuarioRepositorio;
import com.otakeiros.otakusa.entidades.Usuario;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    public Toolbar toob;
    private UsuarioRepositorio mRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        mRepositorio = new UsuarioRepositorio(getApplication());

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Cadastro Usuario");
    }

    public void confirmar_cadastro(View view) {
        String nome;
        String senha;
        String nick;
        String email;
        String frase;
        String confirmacao_senha;

        EditText et_nome = findViewById(R.id.et_nome_usuario);
        EditText et_senha = findViewById(R.id.et_senha_usuario_cadastro_usuario);
        EditText et_nick = findViewById(R.id.et_nick_usuario);
        EditText et_email = findViewById(R.id.et_email_usuario_cadastro_usuario);
        EditText et_frase = findViewById(R.id.et_frase_efeito_usuario_cadastro);
        EditText et_conirmacao_senha = findViewById(R.id.et_confirmacao_senha_cadastro_usuario);

        if (et_nome.getText().toString().equals(""))
            et_nome.requestFocus();
        else if (et_senha.getText().toString().equals(""))
            et_senha.requestFocus();
        else if (et_nick.getText().toString().equals(""))
            et_nick.requestFocus();
        else if (et_email.getText().toString().equals(""))
            et_email.requestFocus();
        else if (et_frase.getText().toString().equals(""))
            et_frase.requestFocus();
        else if (et_conirmacao_senha.getText().toString().equals(""))
            et_conirmacao_senha.requestFocus();
        else {
            nome = et_nome.getText().toString();
            senha = et_senha.getText().toString();
            nick = et_nick.getText().toString();
            email = et_email.getText().toString();
            confirmacao_senha = et_conirmacao_senha.getText().toString();
            frase = et_frase.getText().toString();

            if (senha.equals(confirmacao_senha)) {

                Usuario user = new Usuario();

                user.setEmail(email);
                user.setFraseEfeito(frase);
                user.setNick(nick);
                user.setNome(nome);
                user.setSenha(senha);

                mRepositorio.inserirUsuario(user);
                startActivity(new Intent(getApplicationContext(),Login.class));

            } else {
                et_conirmacao_senha.requestFocus();
                //senha não condiz com a senha de confirmação;
            }

        }

    }
}
