package br.com.cardapio.model;


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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column(name = "razaSocial")
    private String razaoSocial;

    @Column(name = "nomeFantasia")
    private String nomeFantasia;

    @Column(name = "cpfCnpj")
    private String cpfCnpj;

    @Enumerated(EnumType.ORDINAL)
    private SituacaoCliente situacaoCliente;

    @ManyToOne()
    @JoinColumn(name = "tipo_cliente_id")
    private TipoCliente tipoCliente;

    @Transient
    private Integer pageSize;

    @Transient
    private Integer pageNumber;

}
