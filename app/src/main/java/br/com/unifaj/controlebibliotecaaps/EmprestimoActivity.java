package br.com.unifaj.controlebibliotecaaps;

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

import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmprestimoActivity extends AppCompatActivity {


    EditText cliente, livro, data, prazo;
    Button registrar, verEmprestimos, voltar;

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
                        this,
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
                    .enqueue(new Callback<Void>() {

                        @Override
                        public void onResponse(
                                Call<Void> call,
                                Response<Void> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        EmprestimoActivity.this,
                                        "Empréstimo registrado!",
                                        Toast.LENGTH_SHORT
                                ).show();

                                cliente.setText("");
                                livro.setText("");
                                data.setText("");
                                prazo.setText("");

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
                                Call<Void> call,
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
