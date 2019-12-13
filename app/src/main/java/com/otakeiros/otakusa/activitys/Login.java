package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.otakeiros.otakusa.MainActivity;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.UsuarioDao;
import com.otakeiros.otakusa.banco.repositorios.UsuarioRepositorio;
import com.otakeiros.otakusa.entidades.Usuario;

import java.security.spec.ECField;

import static com.otakeiros.otakusa.MainActivity.USUARIO_LOGADO;

public class Login extends AppCompatActivity {
    private UsuarioRepositorio mRepositorio;
    private UsuarioDao mDao;
    public Toolbar toob;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRepositorio = new UsuarioRepositorio(getApplication());
        EntitysRoomDatabase database = EntitysRoomDatabase.getDatabase(getApplication());
        mDao = database.userDao();
    }

    public void confirmar_login(View view) {
        String senha;
        EditText et_login = findViewById(R.id.et_login_login);
        EditText et_senha = findViewById(R.id.et_senha_login);

        if (et_login.getText().toString().equals(""))
            et_login.requestFocus();
        else if (et_senha.getText().toString().equals(""))
            et_senha.requestFocus();
        else {
            login = et_login.getText().toString();
            senha = et_senha.getText().toString();
            validarUsuario(login, senha);
        }
    }

    private void validarUsuario(String email, String senha) {
        new validarUserAsyncTask(mDao).execute(email, senha);
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastrarUsuarioActivity.class));
    }

    private class validarUserAsyncTask extends AsyncTask<String, Void, Boolean> {
        public UsuarioDao dao;

        public validarUserAsyncTask(UsuarioDao userDao) {
            dao = userDao;
        }

        @Override
        protected Boolean doInBackground(String... string) {
            Usuario user = new Usuario();
            try{
                user = dao.get_user(string[0]).get(0);
                if (string[1].equals(user.getSenha()))
                    return true;
            }catch (Exception e){
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) entrar();
            else alertError();
        }
    }

    private void alertError() {
        Toast toast = Toast.makeText(this, "Login invalido", Toast.LENGTH_LONG);
        toast.show();
    }

    private void entrar() {
        USUARIO_LOGADO = login;
        startActivity(new Intent(this, MainActivity.class));
    }
}
