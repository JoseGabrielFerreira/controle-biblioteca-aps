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

public class ListaClientesActivity extends AppCompatActivity {

    // DECLARANDO AS VARIÁVEIS
    TextView clientes;
    Button voltarTelaClientesCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes_cadastrados);

        // RECEBER A LISTA DE CLIENTES
        ArrayList<String> listaClientes = getIntent().getStringArrayListExtra("clientes");

        // CONECTANDO COMPONENTES DA TELA COM AS VARIÁVEIS DO CÓDIGO
        clientes = findViewById(R.id.clientestextView);
        voltarTelaClientesCadastrados = findViewById(R.id.btn_voltar_telaclientes_cadastrados);

        // MOSTRAR CLIENTES
        if (listaClientes != null && !listaClientes.isEmpty()) { // SE A LISTA EXISTIR E NÃO ESTIVER VAZIA

            clientes.setText(""); //DEIXANDO O TEXTVIEW LIMPO

            for (String cliente : listaClientes) { // PARA CADA CLIENTE DENTRO DA LISTA
                clientes.append(cliente + "\n\n"); // ADICIONA CADA CLIENTE NO TEXT VIEW, PULANDO UMA LINHA
            }

        } else { // SENÃO
            clientes.setText("Nenhum cliente cadastrado."); // RESULTADO QUE NENHUM CLIENTE FOI ENCONTRADO
        }

        // BOTÃO VOLTAR
        voltarTelaClientesCadastrados.setOnClickListener(v -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}