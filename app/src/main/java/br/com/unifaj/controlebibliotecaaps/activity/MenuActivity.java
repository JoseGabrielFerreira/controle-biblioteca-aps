package br.com.unifaj.controlebibliotecaaps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.unifaj.controlebibliotecaaps.R;

public class MenuActivity extends AppCompatActivity {

    Button livros;
    Button clientes;
    Button emprestimos;
    Button sair;
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        livros = findViewById(R.id.btn_livros_usuario);
        clientes = findViewById(R.id.btn_clientes);
        emprestimos = findViewById(R.id.btn_emprestimos_usuario);
        sair = findViewById(R.id.btn_sair_usuario);
        voltar = findViewById(R.id.btn_voltar_menu);

        // LIVROS
        livros.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    LivrosActivity.class
            );
            startActivity(intent);
        });

        // CLIENTES
        clientes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    ClientesActivity.class
            );
            startActivity(intent);
        });

        // EMPRÉSTIMOS
        emprestimos.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    EmprestimoActivity.class
            );
            startActivity(intent);
        });

        // SAIR - FECHA O APP
        sair.setOnClickListener(v -> {
            finishAffinity();
        });

        // VOLTAR - VOLTA PARA LOGIN
        voltar.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
            finish();
        });

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