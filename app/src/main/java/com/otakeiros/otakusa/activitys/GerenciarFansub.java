package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.otakeiros.otakusa.R;

public class GerenciarFansub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_fansub);
    }

    public void atualiza(View view) {
        String nome;
        EditText et_nome = findViewById(R.id.et_nome_gerenciar_fansub);
        if (et_nome.getText().toString().equals(""))
            et_nome.requestFocus();
        else {
            nome = et_nome.getText().toString();
        }
    }
}
