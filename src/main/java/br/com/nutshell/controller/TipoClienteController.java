package br.com.nutshell.controller;

import br.com.nutshell.dto.TipoClienteDto;
import br.com.nutshell.service.TipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tiposClientes")
public class TipoClienteController {
    @Autowired
    private TipoClienteService tipoClienteService;

    @GetMapping
    public Page<TipoClienteDto> listar(@PageableDefault(size = 10) Pageable paginacao){
        return tipoClienteService.getAllTiposClientes(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoClienteDto> getTipoClienteById(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        TipoClienteDto dto = tipoClienteService.getTiposClientesById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TipoClienteDto> cadastrar(@RequestBody TipoClienteDto dto, UriComponentsBuilder uriBuilder) {
        TipoClienteDto tipoClienteDto = tipoClienteService.criarTipoCliente(dto);
        URI endereco = uriBuilder.path("/tiposClientes/{id}").buildAndExpand(tipoClienteDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(tipoClienteDto);
    }

    @PutMapping
    public ResponseEntity<TipoClienteDto> update(@RequestBody TipoClienteDto dto) {
        TipoClienteDto tipoCliente = tipoClienteService.updateTipoCliente(dto.getId(), dto);

        return ResponseEntity.ok(tipoCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoClienteDto> remover(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        tipoClienteService.excluirTipoCliente(id);

        return ResponseEntity.noContent().build();
    }
}
