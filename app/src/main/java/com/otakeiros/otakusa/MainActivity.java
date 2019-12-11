package com.otakeiros.otakusa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;

import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.activitys.CadastrarAnimeActivity;
import com.otakeiros.otakusa.activitys.CadastrarUsuarioActivity;
import com.otakeiros.otakusa.activitys.GerenciarFansub;
import com.otakeiros.otakusa.activitys.Login;
import com.otakeiros.otakusa.activitys.PerfilUsuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Toolbar toob;
    public static Boolean USUARIO_LOGADO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Animes");


        if (!USUARIO_LOGADO)
            startActivity(new Intent(getApplicationContext(), Login.class));

        Spinner spinnerLetra = findViewById(R.id.sp_alfabeto);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alafbeto, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLetra.setAdapter(adapter);

        Spinner spinnerAno = findViewById(R.id.sp_ano);
        ArrayAdapter<CharSequence> adapterAno = ArrayAdapter.createFromResource(this, R.array.ano, android.R.layout.simple_spinner_dropdown_item);
        adapterAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAno.setAdapter(adapterAno);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.gerenciarFansub:
                intent = new Intent(this, GerenciarFansub.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                intent = new Intent(this, PerfilUsuario.class);
                startActivity(intent);
                break;
            case R.id.cadastrarAnime:
                intent = new Intent(this, CadastrarAnimeActivity.class);
                startActivity(intent);
                break;
            case R.id.deslogar:
                finish();
                intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
