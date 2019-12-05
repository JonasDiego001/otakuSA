package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.otakeiros.otakusa.R;

public class PerfilUsuario extends AppCompatActivity {
    private Integer a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

    }

    public void editar_usuario(View view) {
        startActivity(new Intent(this,EditarUsuario.class));
    }
}
