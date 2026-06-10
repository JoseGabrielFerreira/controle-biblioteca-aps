package br.com.unifaj.controlebibliotecaaps.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import br.com.unifaj.controlebibliotecaaps.model.Cliente;
import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaClientesActivity extends AppCompatActivity {

    TextView clientes;
    Button voltarTelaClientesCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes_cadastrados);

        clientes = findViewById(R.id.clientestextView);

        voltarTelaClientesCadastrados =
                findViewById(R.id.btn_voltar_telaclientes_cadastrados);

        ApiService apiService =
                ApiClient.getRetrofit().create(ApiService.class);

        apiService.listarClientes()
                .enqueue(new Callback<List<Cliente>>() {

                    @Override
                    public void onResponse(
                            Call<List<Cliente>> call,
                            Response<List<Cliente>> response
                    ) {

                        if (response.isSuccessful()
                                && response.body() != null) {

                            List<Cliente> listaClientes =
                                    response.body();

                            clientes.setText("");

                            for (Cliente cliente : listaClientes) {

                                clientes.append(
                                        "Nome: " + cliente.getNome() +
                                                "\nCPF: " + cliente.getCpf() +
                                                "\nTelefone: " + cliente.getTelefone() +
                                                "\nEmail: " + cliente.getEmail() +
                                                "\n\n"
                                );
                            }

                        } else {

                            clientes.setText(
                                    "Nenhum cliente encontrado."
                            );
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<List<Cliente>> call,
                            Throwable t
                    ) {

                        clientes.setText(
                                "Erro ao conectar ao servidor."
                        );

                        t.printStackTrace();
                    }
                });

        voltarTelaClientesCadastrados.setOnClickListener(
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