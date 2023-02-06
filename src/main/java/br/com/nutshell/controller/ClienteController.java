package br.com.nutshell.controller;

import br.com.nutshell.dto.ClienteDto;
import br.com.nutshell.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<ClienteDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return clienteService.getAllClientes(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        ClienteDto dto = clienteService.getClienteById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ClienteDto dto, UriComponentsBuilder uriBuilder) {
        URI endereco = null;

        try {
            ClienteDto cliente = clienteService.criarCliente(dto);
            endereco = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(endereco).body(cliente);
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.badRequest().body(entityNotFoundException.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ClienteDto dto, UriComponentsBuilder uriBuilder) {
        URI endereco = null;

        try {
            ClienteDto cliente = clienteService.updateCliente(dto.getId(), dto);
            endereco = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(endereco).body(cliente);
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.badRequest().body(entityNotFoundException.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        clienteService.excluirCliente(id);

        return ResponseEntity.noContent().build();
    }


}
