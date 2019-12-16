package com.otakeiros.otakusa.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.otakeiros.otakusa.MainActivity;
import com.otakeiros.otakusa.R;
import com.otakeiros.otakusa.banco.dao.EntitysRoomDatabase;
import com.otakeiros.otakusa.banco.dao.FansubDao;
import com.otakeiros.otakusa.banco.repositorios.AnimeRepositorio;
import com.otakeiros.otakusa.banco.repositorios.FansubRepositorio;
import com.otakeiros.otakusa.banco.repositorios.ImagemRepositorio;
import com.otakeiros.otakusa.entidades.Anime;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.otakeiros.otakusa.entidades.Fansub;
import com.otakeiros.otakusa.entidades.Imagem;
import com.otakeiros.otakusa.entidades.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CadastrarAnimeActivity extends AppCompatActivity {
    public Toolbar toob;
    private AnimeRepositorio mRepositorio;
    private FansubRepositorio mRepositorio2;
    private FansubDao mDao;
    public List<Integer> ids;
    public List<String> conteiner;
    public Integer idSelecionado;
    public Integer posicaoSelecionada = 0;
    public List<Boolean> habilitado;
    public static volatile int CONTROLE = 0;
    private String login;
    public Bitmap mBitmap;
    public String nomeImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anime);
        mRepositorio = new AnimeRepositorio(getApplication());

        EntitysRoomDatabase db = EntitysRoomDatabase.getDatabase(getApplication());
        mRepositorio2 = new FansubRepositorio(getApplication());
        mDao = db.fansubDao();
        new validarFansubAsyncTask(mDao).execute();

        toob = (Toolbar) findViewById(R.id.tubar);
        setSupportActionBar(toob);
        getSupportActionBar().setTitle("Cadrastro Anime");

        TextView tv_sexo = findViewById(R.id.tv_Selecionar_Fansub);
        tv_sexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSexoAlertDialog(v);
            }
        });
    }

    public void confirmar_cadastro(View view) {
        String nome;
        Integer ano;
        Integer numEpisodio;
        double notaInicial;
        String sinopse;

        EditText et_nome = findViewById(R.id.et_nome_usuario_cadastro_anime);
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
        else {
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
            nomeImagem = nome + sinopse;
            salvarFoto(mBitmap);
            mRepositorio.inserirAnime(anim);

            et_nome.setText("");
            et_ano.setText("");
            et_numEpisodio.setText("");
            et_notaInicial.setText("");
            et_sinopse.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastraranime, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.gerenciarFansub:
                intent = new Intent(this, GerenciarFansub.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                intent = new Intent(this, PerfilUsuario.class);
                startActivity(intent);
                break;
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getSexoAlertDialog(final View v) {

        final TextView tv = findViewById(R.id.tv_Selecionar_Fansub);
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarAnimeActivity.this);
        builder.setTitle("Selecione seu sexo");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                CadastrarAnimeActivity.this,
                android.R.layout.select_dialog_singlechoice);


        for (int i = 0; i < conteiner.size(); i++) {
            arrayAdapter.add(conteiner.get(i));
        }
        builder.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("nelore", arrayAdapter.getItem(which));
                        posicaoSelecionada = which;
                        dialog.dismiss();
                        Log.i("nelore", "which ::: -> " + posicaoSelecionada);
                        tv.setText(conteiner.get(posicaoSelecionada));

                    }
                });
        builder.show();

    }

    public void buscarImagem(View view) {
        String local = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "otakuSA";
        Log.i("NELORE","local>>> "+local);
        criaPasta(local);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 0);
    }

    private void criaPasta(String local) {
        File folder = new File(local);
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success)
            Log.i("NELORE", "ROOOT CRIADO");
        else Log.i("NELORE", "ROOT JA EXISTE");
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mBitmap = selectedImage;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Foto não recebida", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Nenhuma imagem selecionada", Toast.LENGTH_LONG).show();
        }
    }

    private void salvarFoto(Bitmap selectedImage) {
        Log.i("NELORE", "SALVANDO FOTO");
        nomeImagem = nomeImagem.replaceAll("\\s", "");
        File local = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "otakusa");

        boolean success = true;
        Log.i("NELORE", "SALVANDO FOTO EM :: "+local.getAbsolutePath());
        if (!local.exists()) {
            success = local.mkdirs();
        }
        if (success) {
            Log.i("NELORE", "DEU BOM");
        } else {
            Log.i("NELORE", "SAPOHA JÁ EXISTE");
        }
        File dir1 = new File(local.getAbsolutePath());
        if(!dir1.exists()){
            dir1.mkdirs();
            Log.i("NELORE",">> "+dir1.exists());
            Log.i("NELORE", "SAPOHA EXISTE AGORA");
        }
        File imagem = null;
        try {
            imagem = File.createTempFile(nomeImagem, ".jpg", local);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File dir = new File(local,nomeImagem+".jpg");
        if(!dir.exists()){
            dir.mkdirs();
            Log.i("NELORE",">> "+dir.exists());
            Log.i("NELORE", "SAPOHA EXISTE AGORA");
        }
        try {
            if(imagem!=null) {
                FileOutputStream fOut = new FileOutputStream(imagem);
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
                Log.i("NELORE", "DEU BOM");
                salvarNoBanco(imagem.getAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarNoBanco(String absolutePath) {
        Imagem imagem = new Imagem();
        imagem.setCaminoImagem(absolutePath);
        ImagemRepositorio repositorio = new ImagemRepositorio(getApplication());
        repositorio.inserirImagem(imagem);
    }

    public class validarFansubAsyncTask extends AsyncTask<Void, Void, List> {
        public FansubDao dao;

        public validarFansubAsyncTask(FansubDao fansubDao) {
            dao = fansubDao;
        }

        @Override
        protected List<Fansub> doInBackground(Void... string) {
            List<Fansub> fansubs = new ArrayList<Fansub>();
            fansubs = dao.getAllFansub();
            return fansubs;
        }

        @Override
        protected void onPostExecute(List todasFansubs) {
            super.onPostExecute(todasFansubs);
            if (todasFansubs.size() > 0) preencherDados(todasFansubs);

        }

        private void preencherDados(List<Fansub> dados) {

            Log.i("NELORE", "size --> " + dados.size());
            conteiner = new ArrayList<>();
            ids = new ArrayList<>();
            habilitado = new ArrayList<>();
            for (int i = 0; i < dados.size(); i++) {
                if (dados.get(i).getHabilitado() == true) {
                    conteiner.add(dados.get(i).getNome());
                    ids.add(dados.get(i).getId());
                    habilitado.add(dados.get(i).getHabilitado());
                    Log.i("nelore", "LOOOOPPP");
                }

            }
        }
    }
}
