package br.com.unifaj.controlebibliotecaaps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.unifaj.controlebibliotecaaps.R;
import br.com.unifaj.controlebibliotecaaps.api.ApiClient;
import br.com.unifaj.controlebibliotecaaps.api.ApiService;
import br.com.unifaj.controlebibliotecaaps.model.LoginRequest;
import br.com.unifaj.controlebibliotecaaps.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsuario, edtSenha;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {

            String login = edtUsuario.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (login.isEmpty() || senha.isEmpty()) {

                Toast.makeText(
                        LoginActivity.this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    ApiClient.getRetrofit()
                            .create(ApiService.class);

            LoginRequest request =
                    new LoginRequest(login, senha);

            apiService.login(request)
                    .enqueue(new Callback<Usuario>() {

                        @Override
                        public void onResponse(
                                Call<Usuario> call,
                                Response<Usuario> response
                        ) {

                            if (response.isSuccessful()
                                    && response.body() != null) {

                                Usuario usuario = response.body();

                                Intent intent;

                                if (usuario.getPerfil().equals("ADMIN")) {

                                    intent = new Intent(
                                            LoginActivity.this,
                                            MenuActivity.class
                                    );

                                } else {

                                    intent = new Intent(
                                            LoginActivity.this,
                                            MenuUsuarioActivity.class
                                    );
                                }

                                startActivity(intent);
                                finish();

                            } else {

                                Toast.makeText(
                                        LoginActivity.this,
                                        "Usuário ou senha inválidos",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }

                        @Override
                        public void onFailure(
                                Call<Usuario> call,
                                Throwable t
                        ) {

                            Toast.makeText(
                                    LoginActivity.this,
                                    "Erro ao conectar com o servidor",
                                    Toast.LENGTH_LONG
                            ).show();

                            t.printStackTrace();
                        }
                    });
        });
    }
}