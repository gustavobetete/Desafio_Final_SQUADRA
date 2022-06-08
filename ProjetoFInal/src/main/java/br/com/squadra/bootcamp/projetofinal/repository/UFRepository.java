package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import br.com.squadra.bootcamp.projetofinal.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Long> {

    Optional<UF> findByNome(String nome);

    Optional<UF> findByCodigoUF(Long codigoUF);

    Page<UF> findBySigla(Pageable paginacao, Sigla sigla);

    Page<UF> findByCodigoUF(Pageable paginacao, Long codigoUF);
}
