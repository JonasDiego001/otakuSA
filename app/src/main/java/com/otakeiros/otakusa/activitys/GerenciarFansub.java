package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.otakeiros.otakusa.R;

import java.util.ArrayList;

public class GerenciarFansub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_fansub);
        ListView listaFansub = (ListView) findViewById(R.id.lista_fansub);
        ArrayList<String> fansubs = preencherDados();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fansubs);
        listaFansub.setAdapter(arrayAdapter);
    }

    private ArrayList<String> preencherDados(){
        ArrayList<String> dados = new ArrayList<String>();
        dados.add("Fansub1");
        dados.add("Fansub2");
        dados.add("Fansub3");
        dados.add("Fansub2");
        dados.add("Fansub3");
        return  dados;
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
