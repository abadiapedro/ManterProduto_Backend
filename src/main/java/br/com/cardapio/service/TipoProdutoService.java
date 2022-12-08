package br.com.cardapio.service;

import br.com.cardapio.dto.TipoProdutoDto;
import br.com.cardapio.model.TipoProduto;
import br.com.cardapio.repository.TipoProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<TipoProdutoDto> getAllTiposProdutos (Pageable paginacao){
        return tipoProdutoRepository.findAll(paginacao)
                .map(tipoProduto -> modelMapper.map(tipoProduto, TipoProdutoDto.class));
    }
    public TipoProdutoDto getTiposProdutosById(Long id){
        TipoProduto tipoProduto = tipoProdutoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Tipo Produto não encontrado")
        );

        return modelMapper.map(tipoProduto, TipoProdutoDto.class);
    }

    public TipoProdutoDto criarTipoProduto(TipoProdutoDto tipoProdutoDto){
        TipoProduto tipoProduto = modelMapper.map(tipoProdutoDto, TipoProduto.class);
        tipoProduto = tipoProdutoRepository.save(tipoProduto);

        return modelMapper.map(tipoProduto, TipoProdutoDto.class);
    }

    public TipoProdutoDto updateTipoProduto(Long id, TipoProdutoDto tipoProdutoDto){
        TipoProduto tipoProduto = modelMapper.map(tipoProdutoDto, TipoProduto.class);
        tipoProduto.setId(id);
        tipoProduto = tipoProdutoRepository.save(tipoProduto);

        return modelMapper.map(tipoProduto, TipoProdutoDto.class);
    }

    public void excluirTipoProduto(Long id){
        tipoProdutoRepository.deleteById(id);
    }
}
