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

public class ClientesActivity extends AppCompatActivity {

    EditText nome, cpf, telefone, endereco;
    Button cadastrarClientes, clientesCadastrados, voltarCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes);

        nome = findViewById(R.id.nomeClienteTextText);
        cpf = findViewById(R.id.cpfTextText);
        telefone = findViewById(R.id.telefoneEditTextText);
        endereco = findViewById(R.id.enderecoEditTextText);

        cadastrarClientes = findViewById(R.id.btn_cadastrat_cliente);
        clientesCadastrados = findViewById(R.id.btn_clientes_cadastrados);
        voltarCC = findViewById(R.id.btn_voltar_CC);

        cadastrarClientes.setOnClickListener(v -> {

            String nomeStr = nome.getText().toString().trim();
            String cpfStr = cpf.getText().toString().trim();
            String telefoneStr = telefone.getText().toString().trim();
            String emailStr = endereco.getText().toString().trim();

            if (nomeStr.isEmpty()
                    || cpfStr.isEmpty()
                    || telefoneStr.isEmpty()
                    || emailStr.isEmpty()) {

                Toast.makeText(
                        ClientesActivity.this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Cliente cliente = new Cliente(
                    nomeStr,
                    cpfStr,
                    telefoneStr,
                    emailStr
            );

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.cadastrarCliente(cliente)
                    .enqueue(new Callback<Void>() {

                        @Override
                        public void onResponse(
                                Call<Void> call,
                                Response<Void> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        ClientesActivity.this,
                                        "Cliente cadastrado com sucesso!",
                                        Toast.LENGTH_SHORT
                                ).show();

                                nome.setText("");
                                cpf.setText("");
                                telefone.setText("");
                                endereco.setText("");

                            } else {

                                Toast.makeText(
                                        ClientesActivity.this,
                                        "Erro ao cadastrar cliente",
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
                                    ClientesActivity.this,
                                    "Erro ao conectar com servidor",
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();
                        }
                    });
        });

        clientesCadastrados.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ClientesActivity.this,
                    ListaClientesActivity.class
            );

            startActivity(intent);
        });

        voltarCC.setOnClickListener(v -> finish());

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