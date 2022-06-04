package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findByNome(String nome);
}
