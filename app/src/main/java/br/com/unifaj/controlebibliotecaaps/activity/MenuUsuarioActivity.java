package br.com.unifaj.controlebibliotecaaps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.com.unifaj.controlebibliotecaaps.R;

public class MenuUsuarioActivity extends AppCompatActivity {

    Button btnLivros;
    Button btnEmprestimos;
    Button btnDevolver;
    Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        btnLivros = findViewById(R.id.btn_livros_usuario);
        btnEmprestimos = findViewById(R.id.btn_emprestimos_usuario);
        btnDevolver = findViewById(R.id.btn_devolver_usuario);
        btnSair = findViewById(R.id.btn_sair_menu);

        btnLivros.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuUsuarioActivity.this,
                    ListaLivrosActivity.class
            );

            startActivity(intent);
        });

        btnEmprestimos.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuUsuarioActivity.this,
                    ListaEmprestimosActivity.class
            );

            startActivity(intent);
        });

        btnDevolver.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuUsuarioActivity.this,
                    DevolverLivroActivity.class
            );

            startActivity(intent);
        });

        btnSair.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuUsuarioActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);

            finish();
        });
    }
}