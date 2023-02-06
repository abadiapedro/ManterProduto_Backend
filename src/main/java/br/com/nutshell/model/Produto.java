package br.com.nutshell.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataCadastro;

    @Enumerated(EnumType.ORDINAL)
    private SituacaoProduto situacaoProduto;

    @ManyToOne()
    @JoinColumn(name = "tipo_produto_id")
    private TipoProduto tipoProduto;

    @Transient
    private Integer pageSize;

    @Transient
    private Integer pageNumber;

}
