package br.com.cardapio.repository;

import br.com.cardapio.model.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
    Optional<TipoProduto> findById(Long id);
}
