package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.otakeiros.otakusa.R;

public class EditarUsuario extends AppCompatActivity {
    public Toolbar toob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Editar Usuario");
    }

    public void confirmar_edicao(View view) {
    }
}
