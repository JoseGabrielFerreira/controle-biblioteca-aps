package br.com.unifaj.controlebibliotecaaps.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.unifaj.controlebibliotecaaps.model.Livro;
import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivrosActivity extends AppCompatActivity {

    EditText titulo, autor, isbn, genero, numPaginas;

    Button cadastrar;
    Button mostrarLivros;
    Button voltar;
    Button excluirLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_livros);

        titulo = findViewById(R.id.titulo_editText);
        autor = findViewById(R.id.autor_editText);
        isbn = findViewById(R.id.isbn_editText);
        genero = findViewById(R.id.genero_editText);
        numPaginas = findViewById(R.id.numpg_editText);

        cadastrar = findViewById(R.id.btn_cadastrar);
        mostrarLivros = findViewById(R.id.btn_mostrarLivros);
        voltar = findViewById(R.id.btn_voltar);
        excluirLivro = findViewById(R.id.btn_excluir_livro);

        cadastrar.setOnClickListener(v -> {

            String tituloStr = titulo.getText().toString().trim();
            String autorStr = autor.getText().toString().trim();
            String isbnStr = isbn.getText().toString().trim();
            String generoStr = genero.getText().toString().trim();
            String paginasStr = numPaginas.getText().toString().trim();

            if (tituloStr.isEmpty()
                    || autorStr.isEmpty()
                    || isbnStr.isEmpty()
                    || generoStr.isEmpty()
                    || paginasStr.isEmpty()) {

                Toast.makeText(
                        LivrosActivity.this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Livro livro = new Livro(
                    tituloStr,
                    autorStr,
                    isbnStr,
                    generoStr,
                    Integer.parseInt(paginasStr),
                    true
            );

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.cadastrarLivro(livro)
                    .enqueue(new Callback<Void>() {

                        @Override
                        public void onResponse(
                                Call<Void> call,
                                Response<Void> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        LivrosActivity.this,
                                        "Livro cadastrado com sucesso!",
                                        Toast.LENGTH_SHORT
                                ).show();

                                limparCampos();

                            } else {

                                Toast.makeText(
                                        LivrosActivity.this,
                                        "Erro ao cadastrar livro",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }

                        @Override
                        public void onFailure(
                                Call<Void> call,
                                Throwable t
                        ) {

                            Toast.makeText(
                                    LivrosActivity.this,
                                    "Erro ao conectar com o servidor",
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();
                        }
                    });
        });

        mostrarLivros.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LivrosActivity.this,
                    ListaLivrosActivity.class
            );

            startActivity(intent);
        });

        excluirLivro.setOnClickListener(v -> {

            String isbnStr = isbn.getText().toString().trim();

            if (isbnStr.isEmpty()) {

                Toast.makeText(
                        LivrosActivity.this,
                        "Digite o ISBN do livro",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.excluirLivro(isbnStr)
                    .enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(
                                Call<String> call,
                                Response<String> response
                        ) {

                            if (response.isSuccessful()) {

                                String mensagem =
                                        response.body() != null
                                                ? response.body()
                                                : "Livro removido com sucesso";

                                Toast.makeText(
                                        LivrosActivity.this,
                                        mensagem,
                                        Toast.LENGTH_SHORT
                                ).show();

                                limparCampos();

                            } else {

                                Toast.makeText(
                                        LivrosActivity.this,
                                        "Erro ao excluir livro",
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
                                    LivrosActivity.this,
                                    "Erro ao conectar com o servidor",
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();
                        }
                    });
        });

        voltar.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }

    private void limparCampos() {

        titulo.setText("");
        autor.setText("");
        isbn.setText("");
        genero.setText("");
        numPaginas.setText("");
    }
}