package br.com.unifaj.controlebibliotecaaps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    //DECLARANDO AS VARIÁVEIS DO CÓDIGO
    Button livros, clientes , emprestimos , sair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        // CONECTANDO AS VARIÁVEIS DO CÓDIGO COM OS COMPONENTES DA TELA
        livros = findViewById(R.id.btn_livros);
        clientes = findViewById(R.id.btn_clientes);
        emprestimos = findViewById(R.id.btn_emprestimos);
        sair = findViewById(R.id.btn_sair);

        // AÇÃO DO BOTÃO LIVROS, QUANDO FOR CLICADO DEVE IR PARA A LivrosActivity
            livros.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, LivrosActivity.class);
            startActivity(intent);
        });

        clientes.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,ClientesActivity.class);
            startActivity(intent);
        });

        emprestimos.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, EmprestimoActivity.class);
            startActivity(intent);
        });

        sair.setOnClickListener(v -> {
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

