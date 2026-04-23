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

public class ListaEmprestimosActivity extends AppCompatActivity {

    // DECLARANDO AS VARIÁVEIS
    TextView emprestimos;
    Button voltarTelaEmprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_emprestimos);

        // RECEBER A LISTA DE EMPRÉSTIMOS
        ArrayList<String> listaEmprestimos = getIntent().getStringArrayListExtra("emprestimos");

        // // CONECTANDO COMPONENTES DA TELA COM AS VARIÁVEIS DO CÓDIGO
        emprestimos = findViewById(R.id.emprestimolivroText);
        voltarTelaEmprestimos = findViewById(R.id.btn_voltar_telaEmprestimo);

        // MOSTRAR EMPRÉSTIMOS
        if (listaEmprestimos != null && !listaEmprestimos.isEmpty()) { // SE A LISTA EXISTIR E NÃO ESTIVER VAZIA

            emprestimos.setText(""); // DEIXANDO O TEXTVIEW LIMPO

            for (String e : listaEmprestimos) { // PARA CADA EMPRÉSTIMO DENTRO DA LISTA
                emprestimos.append(e + "\n\n"); // ADICIONA CADA EMPRÉSTIMO NO TEXT VIEW, PULANDO UMA LINHA
            }

        } else { // SENÃO
            emprestimos.setText("Nenhum empréstimo registrado.");
        }

        // BOTÃO VOLTAR
        voltarTelaEmprestimos.setOnClickListener(v -> {  // RESULTADO QUE NENHUM LIVRO FOI ENCONTRADO
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}