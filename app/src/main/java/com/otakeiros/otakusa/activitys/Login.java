package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.otakeiros.otakusa.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirmar_login(View view) {
        String login;
        String senha;
        EditText et_login = findViewById(R.id.et_login_login);
        EditText et_senha = findViewById(R.id.et_senha_login);

        if (et_login.getText().toString().equals(""))
            et_login.requestFocus();
        else if (et_senha.getText().toString().equals(""))
            et_senha.requestFocus();
        else{
            login = et_login.getText().toString();
            senha = et_senha.getText().toString();
        }
    }

    public void cadastrar(View view) {

    }
}
