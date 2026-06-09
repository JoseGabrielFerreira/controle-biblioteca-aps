package br.com.unifaj.controlebibliotecaaps.api;

import java.util.List;

import br.com.unifaj.controlebibliotecaaps.Cliente;
import br.com.unifaj.controlebibliotecaaps.Emprestimo;
import br.com.unifaj.controlebibliotecaaps.Livro;
import br.com.unifaj.controlebibliotecaaps.model.LoginRequest;
import br.com.unifaj.controlebibliotecaaps.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface ApiService {

    @POST("usuarios/login")
    Call<Usuario> login(
            @Body LoginRequest request
    );

    @GET("livros")
    Call<List<Livro>> listarLivros();

    @POST("livros")
    Call<Void> cadastrarLivro(
            @Body Livro livro
    );

    @PUT("livros/{isbn}")
    Call<String> atualizarLivro(
            @Path("isbn") String isbn,
            @Body Livro livro
    );

    @DELETE("livros/{isbn}")
    Call<String> excluirLivro(
            @Path("isbn") String isbn
    );

    @POST("clientes")
    Call<Void> cadastrarCliente(
            @Body Cliente cliente
    );

    @GET("clientes")
    Call<List<Cliente>> listarClientes();

    @GET("emprestimos")
    Call<List<Emprestimo>> listarEmprestimos();

    @POST("emprestimos")
    Call<Void> cadastrarEmprestimo(
            @Body Emprestimo emprestimo
    );

}