package com.otakeiros.otakusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.otakeiros.otakusa.activitys.CadastrarUsuarioActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(), CadastrarUsuarioActivity.class));


        Spinner spinnerLetra = findViewById(R.id.sp_alfabeto);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.alafbeto,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLetra.setAdapter(adapter);

        Spinner spinnerAno = findViewById(R.id.sp_ano);
        ArrayAdapter<CharSequence> adapterAno = ArrayAdapter.createFromResource(this,R.array.ano,android.R.layout.simple_spinner_dropdown_item);
        adapterAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAno.setAdapter(adapterAno);
    }
}
