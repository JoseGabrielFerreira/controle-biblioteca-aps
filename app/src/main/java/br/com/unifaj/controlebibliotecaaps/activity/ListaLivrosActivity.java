package br.com.unifaj.controlebibliotecaaps.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import br.com.unifaj.controlebibliotecaaps.model.Livro;
import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaLivrosActivity extends AppCompatActivity {

    TextView livrosCadastrados;
    Button voltarLC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_livros);

        livrosCadastrados = findViewById(R.id.livrosCadastradosText);
        voltarLC = findViewById(R.id.btn_voltarLC);

        ApiService apiService =
                ApiClient.getRetrofit()
                        .create(ApiService.class);

        apiService.listarLivros()
                .enqueue(new Callback<List<Livro>>() {

                    @Override
                    public void onResponse(
                            Call<List<Livro>> call,
                            Response<List<Livro>> response
                    ) {

                        if (response.isSuccessful()
                                && response.body() != null) {

                            List<Livro> livros =
                                    response.body();

                            StringBuilder texto =
                                    new StringBuilder();

                            for (Livro livro : livros) {

                                texto.append("Título: ")
                                        .append(livro.getTitulo())
                                        .append("\n");

                                texto.append("Autor: ")
                                        .append(livro.getAutor())
                                        .append("\n");

                                texto.append("ISBN: ")
                                        .append(livro.getIsbn())
                                        .append("\n\n");
                            }

                            livrosCadastrados.setText(
                                    texto.toString()
                            );

                        } else {

                            livrosCadastrados.setText(
                                    "Nenhum livro encontrado."
                            );
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<List<Livro>> call,
                            Throwable t
                    ) {

                        Toast.makeText(
                                ListaLivrosActivity.this,
                                "Erro ao conectar com servidor",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });

        voltarLC.setOnClickListener(v -> finish());

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
}