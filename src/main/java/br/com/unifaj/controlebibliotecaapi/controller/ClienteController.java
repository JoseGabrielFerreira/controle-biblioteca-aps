package br.com.unifaj.controlebibliotecaapi.controller;

import br.com.unifaj.controlebibliotecaapi.model.Cliente;
import br.com.unifaj.controlebibliotecaapi.service.ClienteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(
            ClienteService clienteService
    ) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {

        return clienteService.listarClientes();
    }

    @PostMapping
    public void cadastrarCliente(
            @RequestBody Cliente cliente
    ) {

        clienteService.cadastrarCliente(
                cliente
        );
    }
}