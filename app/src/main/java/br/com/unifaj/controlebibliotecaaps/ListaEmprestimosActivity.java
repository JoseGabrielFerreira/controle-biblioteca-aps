package br.com.unifaj.controlebibliotecaaps;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaEmprestimosActivity extends AppCompatActivity {

    TextView emprestimos;
    Button voltarTelaEmprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_emprestimos);

        emprestimos = findViewById(R.id.emprestimolivroText);
        voltarTelaEmprestimos =
                findViewById(R.id.btn_voltar_telaEmprestimo);

        ApiService apiService =
                ApiClient.getRetrofit()
                        .create(ApiService.class);

        apiService.listarEmprestimos()
                .enqueue(new Callback<List<Emprestimo>>() {

                    @Override
                    public void onResponse(
                            Call<List<Emprestimo>> call,
                            Response<List<Emprestimo>> response
                    ) {

                        if (response.isSuccessful()
                                && response.body() != null) {

                            List<Emprestimo> listaEmprestimos =
                                    response.body();

                            emprestimos.setText("");

                            for (Emprestimo e : listaEmprestimos) {

                                emprestimos.append(
                                        "CPF: " + e.getClienteCpf() +
                                                "\nISBN: " + e.getLivroIsbn() +
                                                "\nData Empréstimo: " + e.getDataEmprestimo() +
                                                "\nData Devolução: " + e.getDataDevolucao() +
                                                "\nStatus: " + e.getStatus() +
                                                "\n\n"
                                );
                            }

                        } else {

                            emprestimos.setText(
                                    "Nenhum empréstimo encontrado."
                            );
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<List<Emprestimo>> call,
                            Throwable t
                    ) {

                        emprestimos.setText(
                                "Erro ao conectar ao servidor."
                        );

                        t.printStackTrace();
                    }
                });

        voltarTelaEmprestimos.setOnClickListener(
                v -> finish()
        );

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