package br.com.cardapio.dto;

import br.com.cardapio.model.SituacaoProduto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataCadastro;
    private SituacaoProduto situacaoProduto;
    private TipoProdutoDto tipoProduto;

}
