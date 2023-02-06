package br.com.nutshell.dto;

import br.com.nutshell.model.SituacaoCliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cpfCnpj;
    private SituacaoCliente situacaoCliente;
    private TipoProdutoDto tipoCliente;

}
