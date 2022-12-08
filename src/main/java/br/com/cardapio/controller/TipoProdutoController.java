package br.com.cardapio.controller;

import br.com.cardapio.dto.TipoProdutoDto;
import br.com.cardapio.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tiposProdutos")
public class TipoProdutoController {
    @Autowired
    private TipoProdutoService tipoProdutoService;

    @GetMapping
    public Page<TipoProdutoDto> listar(@PageableDefault(size = 10) Pageable paginacao){
        return tipoProdutoService.getAllTiposProdutos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProdutoDto> getTipoProdutoById(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        TipoProdutoDto dto = tipoProdutoService.getTiposProdutosById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TipoProdutoDto> cadastrar(@RequestBody TipoProdutoDto dto, UriComponentsBuilder uriBuilder) {
        TipoProdutoDto tipoProdutoDto = tipoProdutoService.criarTipoProduto(dto);
        URI endereco = uriBuilder.path("/tiposProdutos/{id}").buildAndExpand(tipoProdutoDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(tipoProdutoDto);
    }

    @PutMapping
    public ResponseEntity<TipoProdutoDto> update(@RequestBody TipoProdutoDto dto) {
        TipoProdutoDto tipoProduto = tipoProdutoService.updateTipoProduto(dto.getId(), dto);

        return ResponseEntity.ok(tipoProduto);
    }

    @DeleteMapping
    public ResponseEntity<TipoProdutoDto> remover(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        tipoProdutoService.excluirTipoProduto(id);

        return ResponseEntity.noContent().build();
    }
}
