package br.com.cardapio.service;

import br.com.cardapio.dto.ProdutoDto;
import br.com.cardapio.model.Produto;
import br.com.cardapio.model.TipoProduto;
import br.com.cardapio.repository.ProdutoRepository;
import br.com.cardapio.repository.TipoProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProdutoDto> getAllProdutos(Pageable paginacao){
        return produtoRepository.findAll(paginacao)
                .map(produto -> modelMapper.map(produto, ProdutoDto.class));
    }
    public ProdutoDto getProdutoById(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );

        return modelMapper.map(produto, ProdutoDto.class);
    }

    public ProdutoDto criarProduto(ProdutoDto produtoDto) throws Exception {
        Produto produto = modelMapper.map(produtoDto, Produto.class);

        if(produtoDto.getTipoProduto() != null && produto.getTipoProduto().getId() != null){
            TipoProduto tipoProduto = tipoProdutoRepository.findById(produtoDto.getTipoProduto().getId()).orElseThrow(
                    () -> new EntityNotFoundException("Tipo Produto não encontrado"));

            produto.setTipoProduto(tipoProduto);

        }

        produto = produtoRepository.save(produto);

        return modelMapper.map(produto, ProdutoDto.class);
    }

    public ProdutoDto updateProduto(Long id, ProdutoDto produtoDto) throws Exception {
        Produto produto = modelMapper.map(produtoDto, Produto.class);
        produto.setId(id);

        if(produtoDto.getTipoProduto() != null && produto.getTipoProduto().getId() != null){
            TipoProduto tipoProduto = tipoProdutoRepository.findById(produtoDto.getTipoProduto().getId()).orElseThrow(
                    () -> new EntityNotFoundException("Tipo Produto não encontrado"));

            produto.setTipoProduto(tipoProduto);
        }
        produto = produtoRepository.save(produto);

        return modelMapper.map(produto, ProdutoDto.class);
    }

    public void excluirProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
