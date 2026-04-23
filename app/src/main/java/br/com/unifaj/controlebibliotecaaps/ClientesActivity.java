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

public class ClientesActivity extends AppCompatActivity {
    //LISTA QUE GUARDA OS CLIENTES CADASTRADOS
    ArrayList<String> listaClientes = new ArrayList<>();

    //DECLARANDO AS VARIÁVEIS DO CÓDIGO
    EditText nome, cpf, telefone, endereco;
    Button cadastrarClientes, clientesCadastrados, voltarCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes);

        // CONECTANDO AS VARIÁVEIS DO CÓDIGO COM OS COMPONENTES DA TELA
        nome = findViewById(R.id.nomeClienteTextText);
        cpf = findViewById(R.id.cpfTextText);
        telefone = findViewById(R.id.telefoneEditTextText);
        endereco = findViewById(R.id.enderecoEditTextText);
        cadastrarClientes = findViewById(R.id.btn_cadastrat_cliente);
        clientesCadastrados = findViewById(R.id.btn_clientes_cadastrados);
        voltarCC = findViewById(R.id.btn_voltar_CC);

        //AÇÃO DO BOTÃO CADASTRAR CLIENTES
        cadastrarClientes.setOnClickListener(v -> {

            //PEGA OS DADOS DO USUÁRIO E CONVERTE PARA STRING
            String nomeStr = nome.getText().toString();
            String cpfStr = cpf.getText().toString();
            String telefoneStr = telefone.getText().toString();
            String enderecoStr = endereco.getText().toString();

            // MENSAGEM FALANDO QUE O CLIENTE FOI CADSTRADO
            Toast.makeText(this, "Cliente cadastrado com sucesso: " + nomeStr, Toast.LENGTH_SHORT).show();

            // CRIANDO O CLIENTE
            String cliente = nomeStr + " - CPF: "+ cpfStr;

            //SALVANDO CLIENTE NA LISTA
            listaClientes.add(cliente);

            // LIMPAR OS CAMPOS
            nome.setText("");
            cpf.setText("");
            telefone.setText("");
            endereco.setText("");

        });

        clientesCadastrados.setOnClickListener(v -> {
            Intent intent = new Intent(ClientesActivity.this, ListaClientesActivity.class);
            intent.putStringArrayListExtra("clientes", listaClientes); // LEVANDO A LISTA DE LIVROS DESSA TELA PARA A TELA LISTA CLIENTES
            startActivity(intent);
        });

        // AÇÃO DO BOTÃO VOLTAR, QUANDO FOR CLICADO DEVE VOLTAR PARA A PÁGINA ANTERIOR
        voltarCC.setOnClickListener(v -> {
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}