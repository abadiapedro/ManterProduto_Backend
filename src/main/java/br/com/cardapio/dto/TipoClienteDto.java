package br.com.cardapio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoClienteDto {
    private Long id;
    private String nome;
    private String descricao;
}
