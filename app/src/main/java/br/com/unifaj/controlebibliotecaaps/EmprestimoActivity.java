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

import java.util.ArrayList;

public class EmprestimoActivity extends AppCompatActivity {

    // LISTA DE EMPRÉSTIMOS
    ArrayList<String> listaEmprestimos = new ArrayList<>();

    // DECLARANDO AS VARIÁVEIS
    EditText cliente, livro, data, prazo;
    Button registrar, verEmprestimos, voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emprestimo);

        // CONECTANDO AS VARIÁVEIS DO CÓDIGO COM OS COMPONENTES DA TELA
        cliente = findViewById(R.id.nomeClienteeditText);
        livro = findViewById(R.id.livroEmprestimoeditText);
        data = findViewById(R.id.dataEmprestimo_editTextText);
        prazo = findViewById(R.id.editTextText4);

        registrar = findViewById(R.id.btn_registrar_emprestimo);
        verEmprestimos = findViewById(R.id.btn_verEmprestimos);
        voltar = findViewById(R.id.btn_voltarEmprestimos);

        // BOTÃO REGISTRAR
        registrar.setOnClickListener(v -> {

            String clienteStr = cliente.getText().toString();
            String livroStr = livro.getText().toString();
            String dataStr = data.getText().toString();
            String prazoStr = prazo.getText().toString();

            //  Se qualquer campo estiver vazio, mostra uma mensagem e interrompe a execução
            if (clienteStr.isEmpty() || livroStr.isEmpty() || dataStr.isEmpty() || prazoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // CRIANDO EMPRÉSTIMO
            String emprestimo = clienteStr + " pegou " + livroStr +
                    "\nData: " + dataStr +
                    "\nPrazo: " + prazoStr + " dias";

            // SALVANDO EMPRESTIMO NA LISTA
            listaEmprestimos.add(emprestimo);

            Toast.makeText(this, "Empréstimo registrado!", Toast.LENGTH_SHORT).show();

            // LIMPAR CAMPOS
            cliente.setText("");
            livro.setText("");
            data.setText("");
            prazo.setText("");
        });

        // BOTÃO VER EMPRÉSTIMOS
        verEmprestimos.setOnClickListener(v -> {
            Intent intent = new Intent(EmprestimoActivity.this, ListaEmprestimosActivity.class); // LEVANDO A LISTA DE LIVROS DESSA TELA PARA A TELA EMPRESTIMOS
            intent.putStringArrayListExtra("emprestimos", listaEmprestimos);
            startActivity(intent);
        });

        // BOTÃO VOLTAR
        voltar.setOnClickListener(v -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}