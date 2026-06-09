package br.com.unifaj.controlebibliotecaapi.service;

import br.com.unifaj.controlebibliotecaapi.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final List<Cliente> clientes =
            new ArrayList<>();

    public ClienteService() {

        clientes.add(
                new Cliente(
                        "José Gabriel",
                        "12345678900",
                        "19999999999",
                        "jose@email.com"
                )
        );
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public void cadastrarCliente(
            Cliente cliente
    ) {
        clientes.add(cliente);
    }
}