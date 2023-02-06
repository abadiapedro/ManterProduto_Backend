package br.com.nutshell.dto;

import br.com.nutshell.model.SituacaoProduto;
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
