package br.com.cardapio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
}
