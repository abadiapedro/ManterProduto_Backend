package br.com.cardapio.dto;

import br.com.cardapio.model.SituacaoCliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDto {

    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private LocalDate cpfCnpj;
    private SituacaoCliente situacaoCliente;
    private TipoProdutoDto tipoCliente;

}
