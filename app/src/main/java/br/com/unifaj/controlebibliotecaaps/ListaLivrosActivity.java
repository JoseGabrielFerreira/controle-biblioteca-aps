package br.com.unifaj.controlebibliotecaaps;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaLivrosActivity extends AppCompatActivity {

    // DECLARANDO AS VARIÁVEIS
    TextView livrosCadastrados;
    Button voltarLC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_livros);

        // RECEBENDO A LISTA
        ArrayList<String> livros = getIntent().getStringArrayListExtra("livros");

        // CONECTANDO COMPONENTES DA TELA COM AS VARIÁVEIS DO CÓDIGO
        livrosCadastrados = findViewById(R.id.livrosCadastradosText);
        voltarLC = findViewById(R.id.btn_voltarLC);

        // MOSTRAR LIVROS
        if (livros != null && !livros.isEmpty()) { // SE A LISTA EXISTIR E NÃO ESTIVER VAZIA

            livrosCadastrados.setText(""); // DEIXANDO O TEXTVIEW LIMPO

            for (String livro : livros) { // PARA CADA LIVRO DENTRO DA LISTA
                livrosCadastrados.append(livro + "\n\n"); // ADICIONA CADA LIVRO NO TEXT VIEW, PULANDO UMA LINHA
            }

        } else { // SENÃO
            livrosCadastrados.setText("Nenhum livro cadastrado."); // RESULTADO QUE NENHUM LIVRO FOI ENCONTRADO
        }

        // BOTÃO VOLTAR
        voltarLC.setOnClickListener(v -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

