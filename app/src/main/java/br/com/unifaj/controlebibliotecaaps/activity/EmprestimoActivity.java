package br.com.unifaj.controlebibliotecaaps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.unifaj.controlebibliotecaaps.model.Emprestimo;
import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmprestimoActivity extends AppCompatActivity {

    EditText cliente, livro, data, prazo;

    Button registrar;
    Button verEmprestimos;
    Button voltar;
    Button devolver;
    Button excluirEmprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emprestimo);

        cliente = findViewById(R.id.nomeClienteeditText);
        livro = findViewById(R.id.livroEmprestimoeditText);
        data = findViewById(R.id.dataEmprestimo_editTextText);
        prazo = findViewById(R.id.editTextText4);

        registrar = findViewById(R.id.btn_registrar_emprestimo);
        verEmprestimos = findViewById(R.id.btn_verEmprestimos);
        voltar = findViewById(R.id.btn_voltarEmprestimos);
        devolver = findViewById(R.id.btn_devolver);
        excluirEmprestimo = findViewById(R.id.btn_excluir_emprestimo);

        registrar.setOnClickListener(v -> {

            String clienteStr = cliente.getText().toString().trim();
            String livroStr = livro.getText().toString().trim();
            String dataStr = data.getText().toString().trim();
            String prazoStr = prazo.getText().toString().trim();

            if (clienteStr.isEmpty()
                    || livroStr.isEmpty()
                    || dataStr.isEmpty()
                    || prazoStr.isEmpty()) {

                Toast.makeText(
                        EmprestimoActivity.this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Emprestimo emprestimo = new Emprestimo(
                    clienteStr,
                    livroStr,
                    dataStr,
                    prazoStr,
                    "EMPRESTADO"
            );

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.cadastrarEmprestimo(emprestimo)
                    .enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(
                                Call<String> call,
                                Response<String> response
                        ) {

                            if (response.isSuccessful()) {

                                String mensagem = response.body();

                                Toast.makeText(
                                        EmprestimoActivity.this,
                                        mensagem,
                                        Toast.LENGTH_SHORT
                                ).show();

                                if ("Empréstimo realizado".equals(mensagem)) {

                                    cliente.setText("");
                                    livro.setText("");
                                    data.setText("");
                                    prazo.setText("");
                                }

                            } else {

                                Toast.makeText(
                                        EmprestimoActivity.this,
                                        "Erro ao registrar empréstimo",
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
                                    EmprestimoActivity.this,
                                    "Erro ao conectar ao servidor",
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();
                        }
                    });
        });

        verEmprestimos.setOnClickListener(v -> {

            Intent intent = new Intent(
                    EmprestimoActivity.this,
                    ListaEmprestimosActivity.class
            );

            startActivity(intent);
        });

        devolver.setOnClickListener(v -> {

            String isbn = livro.getText().toString().trim();

            if (isbn.isEmpty()) {

                Toast.makeText(
                        EmprestimoActivity.this,
                        "Digite o ISBN do livro",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.devolverLivro(isbn)
                    .enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(
                                Call<String> call,
                                Response<String> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        EmprestimoActivity.this,
                                        response.body(),
                                        Toast.LENGTH_LONG
                                ).show();

                            } else {

                                Toast.makeText(
                                        EmprestimoActivity.this,
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
                                    EmprestimoActivity.this,
                                    "Erro ao conectar ao servidor",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });

        excluirEmprestimo.setOnClickListener(v -> {

            String isbn = livro.getText().toString().trim();

            if (isbn.isEmpty()) {

                Toast.makeText(
                        EmprestimoActivity.this,
                        "Digite o ISBN do livro",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.excluirEmprestimo(isbn)
                    .enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(
                                Call<String> call,
                                Response<String> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        EmprestimoActivity.this,
                                        response.body(),
                                        Toast.LENGTH_LONG
                                ).show();

                                cliente.setText("");
                                livro.setText("");
                                data.setText("");
                                prazo.setText("");

                            } else {

                                Toast.makeText(
                                        EmprestimoActivity.this,
                                        "Erro ao excluir empréstimo",
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
                                    EmprestimoActivity.this,
                                    "Erro ao conectar ao servidor",
                                    Toast.LENGTH_LONG
                            ).show();
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
}