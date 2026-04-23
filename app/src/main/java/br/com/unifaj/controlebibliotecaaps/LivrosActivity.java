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

public class LivrosActivity extends AppCompatActivity {

    // Lista que guarda os livros cadastrados
    ArrayList<String> listaLivros = new ArrayList<>();

    //DECLARANDO AS VARIÁVEIS DO CÓDIGO
    EditText titulo, autor, isbn, genero, numPaginas;
    Button cadastrar, voltar, mostrarLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_livros);

        // CONECTANDO AS VARIÁVEIS DO CÓDIGO COM OS COMPONENTES DA TELA
        titulo = findViewById(R.id.titulo_editText);
        autor = findViewById(R.id.autor_editText);
        isbn = findViewById(R.id.isbn_editText);
        genero = findViewById(R.id.genero_editText);
        numPaginas = findViewById(R.id.numpg_editText);
        cadastrar = findViewById(R.id.btn_cadastrar);
        mostrarLivros = findViewById(R.id.btn_mostrarLivros);
        voltar = findViewById(R.id.btn_voltar);

        // AÇÃO DO BOTÃO CADASTRAR, QUANDO FOR CLICADO
        cadastrar.setOnClickListener(v -> {

            // PEGA OS DADOS DO USUÁRIO E CONVERTE PARA STRING
            String tituloStr = titulo.getText().toString();
            String autorStr = autor.getText().toString();
            String isbnStr = isbn.getText().toString();
            String generoStr = genero.getText().toString();
            String paginasStr = numPaginas.getText().toString();

            // MENSAGEM FALANDO QUE O LIVRO FOI CADSTRADO
            Toast.makeText(this, "Livro cadastrado com sucesso: " + tituloStr, Toast.LENGTH_SHORT).show();

            // CRIANDO O LIVRO
            String livro = tituloStr + " - " + autorStr + " - ISBN: " + isbnStr;

            // SALVANDO NA LISTA
            listaLivros.add(livro);

            // LIMPAR OS CAMPOS
            titulo.setText("");
            autor.setText("");
            isbn.setText("");
            genero.setText("");
            numPaginas.setText("");

        });

        // AÇÃO DO BOTÃO LIVROS CADASTRADOS, QUANDO FOR CLICADO
        mostrarLivros.setOnClickListener(v -> {
            Intent intent = new Intent(LivrosActivity.this, ListaLivrosActivity.class);
            intent.putStringArrayListExtra("livros", listaLivros); // LEVANDO A LISTA DE LIVROS DESSA TELA PARA A TELA LISTA LIVROS
            startActivity(intent);
        });

        // AÇÃO DO BOTÃO VOLTAR, QUANDO FOR CLICADO DEVE VOLTAR PARA A PÁGINA ANTERIOR
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