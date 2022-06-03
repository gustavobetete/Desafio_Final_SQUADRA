package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Long> {


    Optional<UF> findByNome(String nome);
}
