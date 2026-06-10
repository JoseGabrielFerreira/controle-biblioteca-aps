package br.com.unifaj.controlebibliotecaaps.api;

import java.util.List;

import br.com.unifaj.controlebibliotecaaps.model.Cliente;
import br.com.unifaj.controlebibliotecaaps.model.Emprestimo;
import br.com.unifaj.controlebibliotecaaps.model.Livro;
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
    Call<String> cadastrarEmprestimo(
            @Body Emprestimo emprestimo
    );
    @PUT("emprestimos/devolver/{isbn}")
    Call<String> devolverLivro(
            @Path("isbn") String isbn
    );

    @POST("usuarios")
    Call<Void> cadastrarUsuario(
            @Body Usuario usuario
    );

    @GET("usuarios")
    Call<List<Usuario>> listarUsuarios();

    @DELETE("clientes/{cpf}")
    Call<String> excluirCliente(
            @Path("cpf") String cpf
    );

    @DELETE("emprestimos/{isbn}")
    Call<String> excluirEmprestimo(
            @Path("isbn") String isbn
    );


}