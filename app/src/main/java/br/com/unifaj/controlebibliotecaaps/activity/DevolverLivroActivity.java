package br.com.unifaj.controlebibliotecaaps.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevolverLivroActivity extends AppCompatActivity {

    EditText isbn;
    Button devolver;
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devolver_livro);

        isbn = findViewById(R.id.editTextIsbnDevolver);

        devolver = findViewById(R.id.btn_devolver_livro_usuario);
        voltar = findViewById(R.id.btn_voltar_devolver);

        devolver.setOnClickListener(v -> {

            String isbnStr = isbn.getText().toString().trim();

            if (isbnStr.isEmpty()) {

                Toast.makeText(
                        DevolverLivroActivity.this,
                        "Digite o ISBN",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.devolverLivro(isbnStr)
                    .enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(
                                Call<String> call,
                                Response<String> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        DevolverLivroActivity.this,
                                        response.body(),
                                        Toast.LENGTH_LONG
                                ).show();

                                isbn.setText("");

                            } else {

                                Toast.makeText(
                                        DevolverLivroActivity.this,
                                        "Erro ao devolver livro",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }

                        @Override
                        public void onFailure(
                                Call<String> call,
                                Throwable t
                        ) {

                            Toast.makeText(
                                    DevolverLivroActivity.this,
                                    "Erro ao conectar ao servidor",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });

        voltar.setOnClickListener(v -> finish());
    }
}