package br.com.cardapio.controller;

import br.com.cardapio.dto.ProdutoDto;
import br.com.cardapio.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoDto> listar(@PageableDefault(size = 10) Pageable paginacao){
        return produtoService.getAllProdutos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getProdutoById(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        ProdutoDto dto = produtoService.getProdutoById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoDto dto, UriComponentsBuilder uriBuilder) {
        URI endereco = null;

        try{
            ProdutoDto produto = produtoService.criarProduto(dto);
            endereco = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(endereco).body(produto);
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.badRequest().body(entityNotFoundException.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProdutoDto dto, UriComponentsBuilder uriBuilder) {
        URI endereco = null;

        try{
            ProdutoDto produto = produtoService.updateProduto(dto.getId(), dto);
            endereco = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(endereco).body(produto);
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.badRequest().body(entityNotFoundException.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        produtoService.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }


}
