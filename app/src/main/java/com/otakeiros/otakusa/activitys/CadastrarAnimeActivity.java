package com.otakeiros.otakusa.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.repositorios.AnimeRepositorio;
import com.otakeiros.otakusa.entidades.Anime;
import com.otakeiros.otakusa.entidades.Usuario;

public class CadastrarAnimeActivity extends AppCompatActivity {

    private AnimeRepositorio mRepositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anime);
        mRepositorio = new AnimeRepositorio(getApplication());
    }

    public void confirmar_cadastro(View view) {
        String nome;
        Integer ano;
        Integer numEpisodio;
        double notaInicial;
        String sinopse;

        EditText et_nome = findViewById(R.id.et_nota_cadastro_anime);
        EditText et_ano = findViewById(R.id.et_ano_cadastro_anime);
        EditText et_numEpisodio = findViewById(R.id.et_numEpisodio_cadastro_anime);
        EditText et_notaInicial = findViewById(R.id.et_nota_cadastro_anime);
        EditText et_sinopse = findViewById(R.id.et_sinopse_usuario_cadastro);

        if (et_nome.getText().toString().equals(""))
            et_nome.requestFocus();
        else if (et_ano.getText().toString().equals(""))
            et_ano.requestFocus();
        else if (et_numEpisodio.getText().toString().equals(""))
            et_numEpisodio.requestFocus();
        else if (et_notaInicial.getText().toString().equals(""))
            et_notaInicial.requestFocus();
        else if (et_sinopse.getText().toString().equals(""))
            et_sinopse.requestFocus();
        else{
            nome = et_nome.getText().toString();
            ano = Integer.parseInt(et_ano.getText().toString());
            numEpisodio = Integer.parseInt(et_ano.getText().toString());
            notaInicial = Double.parseDouble(et_notaInicial.getText().toString());
            sinopse = et_sinopse.getText().toString();

            Anime anim = new Anime();
            anim.setNome(nome);
            anim.setNumEpisodio(numEpisodio);
            anim.setNumEpisodio(numEpisodio);
            anim.setAnoLancamento(ano);
            anim.setNotaMedia(notaInicial);
            anim.setSinops(sinopse);

            mRepositorio.inserirAnime(anim);
        }
    }
}
