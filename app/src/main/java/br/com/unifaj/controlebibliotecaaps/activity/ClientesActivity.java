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

import br.com.unifaj.controlebibliotecaaps.model.Cliente;
import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientesActivity extends AppCompatActivity {

    EditText nome, cpf, telefone, email, loginU, senhaU;
    Button cadastrarClientes, clientesCadastrados, voltarCC, excluirCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes);

        nome = findViewById(R.id.nomeClienteTextText);
        cpf = findViewById(R.id.cpfTextText);
        telefone = findViewById(R.id.telefoneEditTextText);
        email = findViewById(R.id.emailEditTextText);
        loginU = findViewById(R.id.EditTextTextLogin);
        senhaU = findViewById(R.id.EditTextTextSenha);


        cadastrarClientes = findViewById(R.id.btn_cadastrat_cliente);
        clientesCadastrados = findViewById(R.id.btn_clientes_cadastrados);
        voltarCC = findViewById(R.id.btn_voltar_CC);
        excluirCliente = findViewById(R.id.btn_excluir_cliente);

        cadastrarClientes.setOnClickListener(v -> {

            String nomeStr = nome.getText().toString().trim();
            String cpfStr = cpf.getText().toString().trim();
            String telefoneStr = telefone.getText().toString().trim();
            String emailStr = email.getText().toString().trim();
            String loginStr = loginU.getText().toString().trim();
            String senhaStr = senhaU.getText().toString().trim();

            if (nomeStr.isEmpty()
                    || cpfStr.isEmpty()
                    || telefoneStr.isEmpty()
                    || emailStr.isEmpty()
                    || loginStr.isEmpty()
                    || senhaStr.isEmpty()) {

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
                    emailStr,
                    loginStr,
                    senhaStr
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
                                email.setText("");
                                loginU.setText("");
                                senhaU.setText("");

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

        excluirCliente.setOnClickListener(v -> {

            String cpfStr =
                    cpf.getText().toString().trim();

            if (cpfStr.isEmpty()) {

                Toast.makeText(
                        ClientesActivity.this,
                        "Digite o CPF do cliente",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            apiService.excluirCliente(cpfStr)
                    .enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(
                                Call<String> call,
                                Response<String> response
                        ) {

                            if (response.isSuccessful()) {

                                Toast.makeText(
                                        ClientesActivity.this,
                                        response.body(),
                                        Toast.LENGTH_SHORT
                                ).show();

                                nome.setText("");
                                cpf.setText("");
                                telefone.setText("");
                                email.setText("");
                                loginU.setText("");
                                senhaU.setText("");

                            } else {

                                Toast.makeText(
                                        ClientesActivity.this,
                                        "Erro ao excluir cliente",
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
                                    ClientesActivity.this,
                                    "Erro ao conectar com servidor",
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();
                        }
                    });
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