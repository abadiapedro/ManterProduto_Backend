package br.com.nutshell.repository;

import br.com.nutshell.model.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long> {
    Optional<TipoCliente> findById(Long id);
}
